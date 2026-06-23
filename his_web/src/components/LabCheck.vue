<template>
    <div class="lab-check-page">
        <div class="patient-panel">
            <div class="panel-title">
                <span>患者列表 ({{ filteredPatients.length }})</span>
                <i class="el-icon-refresh" @click="getPatients"></i>
            </div>

            <el-radio-group v-model="patientStatus" size="mini" @change="getPatients">
                <el-radio-button label="visited">已诊</el-radio-button>
                <el-radio-button label="visit">待诊</el-radio-button>
            </el-radio-group>

            <el-input
                size="mini"
                clearable
                class="patient-search"
                placeholder="姓名/卡号/就诊流水号"
                v-model="patientKeyword">
            </el-input>

            <ul class="patient-list">
                <li
                    v-for="(item, index) in filteredPatients"
                    :key="item.rid"
                    :class="{ selected: selectedPatient && selectedPatient.rid === item.rid }"
                    @click="selectPatient(item, index)">
                    <span>{{ item.pname }}</span>
                    <small>{{ item.rid }}</small>
                </li>
            </ul>
        </div>

        <div class="check-panel">
            <div class="check-toolbar">
                <div>
                    <el-button
                        type="primary"
                        size="small"
                        icon="el-icon-plus"
                        :disabled="!selectedPatient"
                        @click="openApplyDialog">
                        开检验申请
                    </el-button>
                    <el-button
                        size="small"
                        icon="el-icon-refresh"
                        @click="getLabChecks">
                        刷新
                    </el-button>
                </div>

                <div class="filter-area">
                    <el-select v-model="checkStatusFilter" size="small" class="status-select">
                        <el-option label="全部状态" value=""></el-option>
                        <el-option label="已申请" value="APPLIED"></el-option>
                        <el-option label="已出结果" value="COMPLETED"></el-option>
                    </el-select>
                    <el-date-picker size="small" v-model="dateRange" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" value-format="yyyy-MM-dd" style="width:220px;"></el-date-picker>
                    <el-input size="small" clearable placeholder="项目/患者/就诊流水号" v-model="checkKeyword"></el-input>
                </div>
            </div>

            <el-descriptions
                v-if="selectedPatient"
                class="patient-info"
                title="当前患者"
                :column="4"
                size="small"
                border>
                <el-descriptions-item label="姓名">{{ selectedPatient.pname }}</el-descriptions-item>
                <el-descriptions-item label="就诊卡号">{{ selectedPatient.pid }}</el-descriptions-item>
                <el-descriptions-item label="就诊流水号">{{ selectedPatient.rid }}</el-descriptions-item>
                <el-descriptions-item label="科室">{{ selectedPatient.dpmtnNme }}</el-descriptions-item>
            </el-descriptions>

            <el-table
                class="check-table"
                :data="filteredLabChecks"
                border
                height="430"
                empty-text="请选择患者或新建检验申请">
                <el-table-column prop="checkId" label="编号" width="60"></el-table-column>
                <el-table-column prop="pname" label="患者" width="80"></el-table-column>
                <el-table-column prop="rid" label="就诊流水号" width="130"></el-table-column>
                <el-table-column prop="checkItem" label="检验项目" width="150"></el-table-column>
                <el-table-column prop="specimen" label="标本" width="80"></el-table-column>
                <el-table-column prop="applyTime" label="申请时间" width="160"></el-table-column>
                <el-table-column label="状态" width="90">
                    <template slot-scope="scope">
                        <el-tag
                            size="mini"
                            :type="scope.row.applyStatus === 'COMPLETED' ? 'success' : 'warning'">
                            {{ statusText(scope.row.applyStatus) }}
                        </el-tag>
                    </template>
                </el-table-column>
                <el-table-column label="结果" min-width="160">
                    <template slot-scope="scope">
                        <span :class="highlightClass(scope.row.resultValue)">{{ scope.row.resultValue }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="操作" width="110">
                    <template slot-scope="scope">
                        <el-button size="mini" type="primary" @click="openEditDialog(scope.row)" :disabled="scope.row.applyStatus === 'COMPLETED'">编辑</el-button>
                        <el-button size="mini" type="danger" @click="deleteCheck(scope.row)">删除</el-button>
                        <el-button size="mini" type="success" :disabled="scope.row.applyStatus === 'COMPLETED'" @click="openResultDialog(scope.row)">结果</el-button>
                    </template>
                </el-table-column>
            </el-table>

            <div class="todo-box">
                <strong>已完成：</strong>
                编辑/删除、日期查询、异常值高亮、结算联动。
            </div>
        </div>

        <el-dialog title="新增检验申请" :visible.sync="applyDialog.visible" width="620px">
            <el-form :model="applyForm" label-width="100px">
                <el-form-item label="检验项目">
                    <el-select v-model="applyForm.checkItem" filterable allow-create placeholder="请选择或输入项目">
                        <el-option label="血常规" value="血常规"></el-option>
                        <el-option label="尿常规" value="尿常规"></el-option>
                        <el-option label="肝功能" value="肝功能"></el-option>
                        <el-option label="肾功能" value="肾功能"></el-option>
                        <el-option label="空腹血糖" value="空腹血糖"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="标本类型">
                    <el-select v-model="applyForm.specimen" placeholder="请选择标本">
                        <el-option label="静脉血" value="静脉血"></el-option>
                        <el-option label="尿液" value="尿液"></el-option>
                        <el-option label="粪便" value="粪便"></el-option>
                        <el-option label="咽拭子" value="咽拭子"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="临床诊断">
                    <el-input v-model="applyForm.clinicalDiagnosis" placeholder="请输入临床诊断"></el-input>
                </el-form-item>
                <el-form-item label="备注">
                    <el-input type="textarea" :rows="3" v-model="applyForm.remark"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer">
                <el-button @click="applyDialog.visible = false">取 消</el-button>
                <el-button type="primary" @click="submitApply">提 交</el-button>
            </span>
        </el-dialog>

        <el-dialog title="编辑检验申请" :visible.sync="editDialog.visible" width="620px">
            <el-form :model="applyForm" label-width="100px">
                <el-form-item label="检验项目">
                    <el-select v-model="applyForm.checkItem" filterable allow-create style="width:100%;">
                        <el-option label="血常规" value="血常规"></el-option>
                        <el-option label="尿常规" value="尿常规"></el-option>
                        <el-option label="肝功能" value="肝功能"></el-option>
                        <el-option label="肾功能" value="肾功能"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="标本类型">
                    <el-select v-model="applyForm.specimen" style="width:100%;">
                        <el-option label="静脉血" value="静脉血"></el-option>
                        <el-option label="尿液" value="尿液"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="临床诊断">
                    <el-input v-model="applyForm.clinicalDiagnosis"></el-input>
                </el-form-item>
                <el-form-item label="备注">
                    <el-input type="textarea" :rows="3" v-model="applyForm.remark"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer">
                <el-button @click="editDialog.visible = false">取 消</el-button>
                <el-button type="primary" @click="submitEdit">保 存</el-button>
            </span>
        </el-dialog>
        <el-dialog title="录入检验结果" :visible.sync="resultDialog.visible" width="620px">
            <el-form :model="resultForm" label-width="100px">
                <el-form-item label="检验项目">
                    <el-input v-model="resultForm.checkItem" disabled></el-input>
                </el-form-item>
                <el-form-item label="结果内容">
                    <el-input type="textarea" :rows="4" v-model="resultForm.resultValue"></el-input>
                </el-form-item>
                <el-form-item label="结果医生">
                    <el-input v-model="resultForm.resultDoctor"></el-input>
                </el-form-item>
                <el-form-item label="备注">
                    <el-input type="textarea" :rows="3" v-model="resultForm.remark"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer">
                <el-button @click="resultDialog.visible = false">取 消</el-button>
                <el-button type="primary" @click="submitResult">保 存</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
import { mapState } from 'vuex'

export default {
    data() {
        return {
            patientStatus: 'visited',
            patientKeyword: '',
            patients: [],
            selectedPatient: null,
            selectedPatientIndex: -1,
            labChecks: [],
            checkKeyword: '',
            checkStatusFilter: '',
            dateRange: null,
            applyDialog: {
                visible: false
            },
            applyForm: {
                checkItem: '血常规',
                specimen: '静脉血',
                clinicalDiagnosis: '',
                remark: ''
            },
            resultDialog: {
                visible: false
            },
            resultForm: {
                checkId: null,
                checkItem: '',
                resultValue: '',
                resultDoctor: '',
                remark: ''
            },
            editDialog: {
                visible: false
            },
            editingCheckId: null
        }
    },
    created() {
        this.getPatients()
        this.getLabChecks()
    },
    methods: {
        async getPatients() {
            const path = this.patientStatus === 'visited' ? '/outvisit/visited/' : '/outvisit/visit/'
            const { data: res } = await this.$http.get(path + this.userInfo.userName)
            this.patients = res.data || []
            if (this.selectedPatient) {
                const current = this.patients.find(item => item.rid === this.selectedPatient.rid)
                this.selectedPatient = current || null
            }
        },
        async getLabChecks() {
            if (!this.userInfo.userName) return
            const { data: res } = await this.$http.get('/labcheck/doctor/' + this.userInfo.userName)
            this.labChecks = res.data || []
        },
        async selectPatient(patient, index) {
            this.selectedPatient = patient
            this.selectedPatientIndex = index
            const { data: res } = await this.$http.get('/labcheck/rid/' + patient.rid)
            this.labChecks = res.data || []
        },
        openApplyDialog() {
            this.applyForm = {
                checkItem: '血常规',
                specimen: '静脉血',
                clinicalDiagnosis: '',
                remark: ''
            }
            this.applyDialog.visible = true
        },
        async submitApply() {
            if (!this.selectedPatient) {
                this.$message.warning('请先选择患者')
                return
            }
            if (!this.applyForm.checkItem) {
                this.$message.warning('请填写检验项目')
                return
            }

            const payload = {
                ...this.applyForm,
                rid: this.selectedPatient.rid,
                pid: this.selectedPatient.pid,
                pname: this.selectedPatient.pname,
                doctName: this.userInfo.userName,
                applyStatus: 'APPLIED',
                applyTime: window.formatDateTimeToNorm()
            }
            // 支付联动：创建检验申请同时生成对应医嘱
            try {
                await this.$http.post('/doctororder', {
                    rid: this.selectedPatient.rid,
                    patiid: this.selectedPatient.pid,
                    ordername: this.applyForm.checkItem,
                    ordertype: '检验',
                    orderprice: 0,
                    orderallprice: 0,
                    totalOrder: 1,
                    ispaid: false,
                    prescriptiontime: window.formatDateTimeToNorm()
                })
            } catch(e) { console.log('医嘱创建忽略', e) }
            const { data: res } = await this.$http.post('/labcheck', payload)
            if (res.code === 200) {
                this.$message.success('检验申请已提交')
                this.applyDialog.visible = false
                this.selectPatient(this.selectedPatient, this.selectedPatientIndex)
            } else {
                this.$message.error('提交失败：' + res.message)
            }
        },
        openResultDialog(row) {
            this.resultForm = {
                checkId: row.checkId,
                checkItem: row.checkItem,
                resultValue: row.resultValue || '',
                resultDoctor: this.userInfo.userName,
                remark: row.remark || ''
            }
            this.resultDialog.visible = true
        },
        async submitResult() {
            if (!this.resultForm.resultValue) {
                this.$message.warning('请填写检验结果')
                return
            }
            const { data: res } = await this.$http.put('/labcheck/result', this.resultForm)
            if (res.code === 200) {
                this.$message.success('检验结果已保存')
                this.resultDialog.visible = false
                if (this.selectedPatient) {
                    this.selectPatient(this.selectedPatient, this.selectedPatientIndex)
                } else {
                    this.getLabChecks()
                }
            } else {
                this.$message.error('保存失败：' + res.message)
            }
        },
        statusText(status) {
            if (status === 'COMPLETED') return '已出结果'
            return '已申请'
        },
        openEditDialog(row) {
            this.applyForm = {
                checkItem: row.checkItem,
                specimen: row.specimen,
                clinicalDiagnosis: row.clinicalDiagnosis,
                remark: row.remark
            }
            this.editingCheckId = row.checkId
            this.editDialog.visible = true
        },
        async submitEdit() {
            const { data: res } = await this.$http.put('/labcheck', {
                checkId: this.editingCheckId,
                ...this.applyForm
            })
            if (res.code === 200) {
                this.$message.success('修改成功')
                this.editDialog.visible = false
                if (this.selectedPatient) this.selectPatient(this.selectedPatient, this.selectedPatientIndex)
            }
        },
        async deleteCheck(row) {
            const confirm = await this.$confirm('确定删除该检验申请吗？', '提示', { type: 'warning' }).catch(() => {})
            if (!confirm) return
            const { data: res } = await this.$http.delete('/labcheck/' + row.checkId)
            if (res.code === 200) {
                this.$message.success('删除成功')
                if (this.selectedPatient) this.selectPatient(this.selectedPatient, this.selectedPatientIndex)
            }
        },
        highlightClass(value) {
            if (!value) return ''
            if (value.includes('↑') && value.includes('↓')) return ''
            if (value.includes('↑')) return 'abnormal-high'
            if (value.includes('↓')) return 'abnormal-low'
            return ''
        },
        matchesKeyword(row, keyword) {
            if (!keyword) return true
            const text = [
                row.pname,
                row.pid,
                row.rid,
                row.checkItem,
                row.specimen,
                row.resultValue
            ].join('|')
            return text.indexOf(keyword) !== -1
        }
    },
    computed: {
        ...mapState(['userInfo']),
        filteredPatients() {
            if (!this.patientKeyword) return this.patients
            return this.patients.filter(item => {
                const text = [item.pname, item.pid, item.rid].join('|')
                return text.indexOf(this.patientKeyword) !== -1
            })
        },
        filteredLabChecks() {
            return this.labChecks.filter(item => {
                const statusMatched = !this.checkStatusFilter || item.applyStatus === this.checkStatusFilter
                const dateMatched = !this.dateRange || (item.applyTime >= this.dateRange[0] && item.applyTime <= this.dateRange[1] + ' 23:59:59')
                return statusMatched && dateMatched && this.matchesKeyword(item, this.checkKeyword)
            })
        }
    }
}
</script>

<style scoped>
.lab-check-page {
    display: flex;
    gap: 12px;
    width: 100%;
    min-width: 980px;
    padding: 10px;
    box-sizing: border-box;
}

.patient-panel {
    width: 180px;
    min-width: 180px;
    height: 620px;
    padding: 12px;
    border: 1px solid #dcdfe6;
    border-radius: 6px;
    box-sizing: border-box;
}

.panel-title {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 8px;
    font-size: 13px;
    font-weight: bold;
}

.panel-title i {
    cursor: pointer;
}

.patient-search {
    margin: 8px 0;
}

.patient-list {
    height: 510px;
    overflow-y: auto;
    list-style: none;
    padding: 0;
    margin: 0;
}

.patient-list li {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 8px;
    margin-bottom: 5px;
    border: 1px solid #dcdfe6;
    border-radius: 4px;
    background-color: #f7f9fc;
    cursor: pointer;
}

.patient-list li:hover,
.patient-list li.selected {
    background-color: #ecf5ff;
    border-color: #409eff;
}

.patient-list small {
    color: #909399;
    font-size: 11px;
}

.check-panel {
    flex: 1;
    min-width: 780px;
}

.check-toolbar {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 10px;
}

.filter-area {
    display: flex;
    gap: 8px;
    width: 360px;
}

.status-select {
    width: 120px;
}

.patient-info {
    margin-bottom: 10px;
}

.check-table {
    width: 100%;
}

.todo-box {
    margin-top: 10px;
    padding: 8px 10px;
    border-left: 4px solid #e6a23c;
    background-color: #fdf6ec;
    color: #606266;
    font-size: 13px;
}
.abnormal-high { color: #f56c6c; font-weight: bold; background-color: #fef0f0; padding: 2px 4px; border-radius: 3px; }
.abnormal-low { color: #e6a23c; font-weight: bold; background-color: #fdf6ec; padding: 2px 4px; border-radius: 3px; }
</style>



