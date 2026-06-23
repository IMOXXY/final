<template>
<div class="patient-out-visit">

<div class="list-container-112">
    <h2 class="list-title">
        患者列表 ({{ patients.length }}) <i class="el-icon-refresh" @click="getPatiToVist" />
    </h2>
    <ul class="custom-list-112 custom-list-113">
        <li
            v-for="(item, index) in patients"

            @click="patientRecord(item, index)"
            :key="item.pid"
            :class="{
                selected:
                    patient_edit_index === index,
            }"
        >
            <span>{{ item.pname }}</span>
            <i v-if="submittedPatients[item.rid]" class="el-icon-success" style="color:#67C23A;margin-left:5px;" title="病历已提交"></i>
            

        </li>
    </ul>
</div>


<el-form label-width="100px" class="medical-form">
    <!-- 主诉 -->
    <el-form-item label="主诉">
      <el-input type="textarea" v-model="form.chiefComplaint" :rows="2" />
    </el-form-item>

    <!-- 现病史 -->
    <el-form-item label="现病史">
      <el-input type="textarea" v-model="form.presentIllness" :rows="2" />
    </el-form-item>

    <!-- 既往史 -->
    <el-form-item label="既往史">
      <el-checkbox-group v-model="form.pastHistory">
        <el-checkbox label="否认慢性病史" />
        <el-checkbox label="高血压" />
        <el-checkbox label="糖尿病" />
        <el-checkbox label="冠心病" />
        <el-checkbox label="脑卒中" />
        <el-checkbox label="慢阻肺" />
        <el-checkbox label="肺结核" />
        <el-checkbox label="肺癌" />
        <el-checkbox label="心梗" />
        <el-checkbox label="肾病" />
        <el-checkbox label="肝炎" />
        <el-checkbox label="精神病" />
        <el-checkbox label="职业病" />
        <el-checkbox label="其他" />
      </el-checkbox-group>
      <el-input type="textarea" placeholder="其他病史：外伤史、手术史、个人史、家族史、婚育史、输血史等" v-model="form.pastOther" :rows="2" />
    </el-form-item>

    <!-- 过敏史 -->
    <el-form-item label="过敏史">
      <el-input v-model="form.allergy" />
    </el-form-item>

    <!-- 体格检查 -->
    <el-form-item label="体格检查">
      <el-radio-group v-model="form.physical">
        <el-radio label="无特殊" />
        <el-radio label="是外伤" />
      </el-radio-group>
    </el-form-item>

    <!-- 辅助检查 -->
    <el-form-item label="辅助检查">
      <el-input v-model="form.assist" />
    </el-form-item>
  
    <el-form-item class="diagnose-icd" label="门诊诊断">
        <el-select v-model="medicalContent.diagnoseICD" filterable > <!-- TODO filter-method="" -->
            <el-option v-for="item in icdCode" 
                :key=item.icd
                :label=item.icdname
                :value=item.icd></el-option>
        </el-select>
        <el-button type="primary" @click="onSaveMedicalContent" v-if="!Boolean(docId)" :disabled="patient_edit_index===-1">提交</el-button>
        <el-button type="primary" v-if="Boolean(docId)" disabled>已提交</el-button>
        <el-button type="success" @click="printDialogVisible = true" :disabled="!Boolean(docId)">打印病历</el-button>
    </el-form-item>
  
</el-form>

<!-- 病历打印 -->
<el-dialog title="病历打印预览" :visible.sync="printDialogVisible" width="600px">
  <div id="printArea" style="font-family:SimSun;padding:20px;line-height:1.8;">
    <h3 style="text-align:center;">================ 病历信息 =================</h3>
    <p>【患者姓名】：{{ medicalContent.pname }}</p>
    <p>【主诉】：{{ form.chiefComplaint }}</p>
    <p>【现病史】：{{ form.presentIllness }}</p>
    <p>【既往史】：{{ form.pastHistory.join('\u3001') }} {{ form.pastOther }}</p>
    <p>【过敏史】：{{ form.allergy }}</p>
    <p>【体格检查】：{{ form.physical }}</p>
    <p>【辅助检查】：{{ form.assist }}</p>
    <p>【门诊诊断】：{{ medicalContent.diagnoseICDName }}</p>
    <hr>
    <p style="text-align:right;">签名医生：{{ userInfo.userName }}&nbsp;&nbsp;&nbsp;&nbsp;日期：{{ medicalContent.visitTime }}</p>
    <p style="text-align:center;">=========================================</p>
  </div>
  <span slot="footer">
    <el-button @click="printDialogVisible = false">关闭</el-button>
    <el-button type="primary" @click="printMedicalRecord">打印</el-button>
  </span>
</el-dialog>

</div>
</template>

<script>
import { mapState, mapMutations, mapActions } from 'vuex'

export default {
    data() {
        return {
            // 患者信息列表
            patients: [],
            patient_edit_index: -1,

            // 病历待保存信息
            medicalContent: {
                // docId: '',
                rid: '',
                pid: '',
                pname: '',
                diagnoseICD: '',
                diagnoseICDName: '',
                medicalContent: '',
                visitTime: ''
            },
            docId: '',
            // 病历结构化信息
            form: {
                chiefComplaint: '',
                presentIllness: '',
                pastHistory: [],
                pastOther: '',
                allergy: '无',  // 过敏史
                physical: '无特殊',
                assist: '无'
            },
            icdCode: [],
            printDialogVisible: false,
            icdSearchKeyword: '',
            submittedPatients: {},

        }
    },
    created() {
        this.getPatiToVist()
        this.getAllICDCode()
        window.medicalRecordV = this
    },
    methods: {
        async getPatiToVist() {
            const {data:res} = await this.$http.get('/outvisit/visit/'+this.userInfo.userName)
            console.log(res)
            this.patients = res.data
        },
        async getAllICDCode() {
            const {data:res} = await this.$http.get('/medicalrecord/icd/all')
            this.icdCode = res.data
            this._allICDCode = [...res.data]
            console.log(
                _.keyBy(this.icdCode,'icd')
            )
        },
        // 诊断首字母筛选
        icdFilterMethod(keyword) {
            this.icdSearchKeyword = keyword
            if (!keyword) return this.icdCode
            const kw = keyword.toLowerCase()
            this.icdCode = this._allICDCode.filter(d => 
                d.icdname.includes(kw) || 
                d.icd.toLowerCase().includes(kw) || 
                (d.inputstr && d.inputstr.toLowerCase().includes(kw))
            )
        },
        resetICDFilter(visible) {
            if (!visible) {
                setTimeout(() => { this.icdCode = this._allICDCode }, 300)
            }
        },
        async patientRecord(patientInfo,index){
            this.patient_edit_index = index
            this.medicalContent.pid = patientInfo.pid
            this.medicalContent.rid = patientInfo.rid
            this.medicalContent.pname = patientInfo.pname
            const {data:res} = await this.$http.get('/medicalrecord/rid/'+patientInfo.rid)
            console.log(res)
            if (res.data.length>0) { // 病历已写
                const mr = res.data[0]
                this.docId = mr.docId
                this.submittedPatients[mr.rid] = true
                this.medicalContent.diagnoseICD = mr.diagnoseICD
                this.medicalContent.diagnoseICDName = mr.diagnoseICDName
                this.medicalContent.medicalContent = mr.medicalContent
                this.medicalContent.visitTime = mr.visitTime
                this.form = JSON.parse(mr.medicalContent)
            }else {
                this.docId = ''
                this.medicalContent = {
                    rid: '',
                    pid: '',
                    pname: '',
                    diagnoseICD: '',
                    diagnoseICDName: '',
                    medicalContent: '',
                    visitTime: ''
                }
            
                this.form = {
                    chiefComplaint: '',
                    presentIllness: '',
                    pastHistory: [],
                    pastOther: '',
                    allergy: '无',  // 过敏史
                    physical: '无特殊',
                    assist: '无'
                }
            }




        },
        async onSaveMedicalContent () {
            const patientInfo = this.patients[this.patient_edit_index]
            this.medicalContent.pid = patientInfo.pid
            this.medicalContent.rid = patientInfo.rid
            this.medicalContent.pname = patientInfo.pname
            this.medicalContent.diagnoseICDName = _.keyBy(this.icdCode,'icd')[this.medicalContent.diagnoseICD].icdname
            this.medicalContent.medicalContent = JSON.stringify(this.form)
            this.medicalContent.visitTime = formatDateTimeToNorm()

            const {data:res} = await this.$http.post('/medicalrecord',this.medicalContent)
            if(res.code===200){
                this.docId = res.data.docId
                this.$message.success('病历保存成功!')
            }else{
                this.$message.error(res.message)
            }

        },
        printMedicalRecord() {
            const content = document.getElementById('printArea')
            if (!content) return
            const win = window.open('', '_blank')
            win.document.write(
                '<html><head><title>病历打印</title>' +
                '<style>body{font-family:SimSun;padding:20px;line-height:1.8;}' +
                'h3{text-align:center;} p{margin:5px 0;}</style></head><body>'
            )
            win.document.write(content.innerHTML)
            win.document.write('</body></html>')
            win.document.close()
            win.print()
        }
    },
    computed: {
        ...mapState(['userInfo'])
    }
}
</script>

<style scoped>
.patient-out-visit {
    width: 870px;
    display: flex;
    justify-content: space-between;
}

.medical-form {
    width: 600px;
    /* margin-left: 10px; */
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.list-container-112 {
    width: 200px;
    padding: 15px;
    padding-top: 8px;
    border: 1px solid #ccc;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    height: 600px;
}

.list-container-112 .list-title {
    color: #333;
    text-align: center;
    font-size: 13px;
}

.list-title i {
    font-weight: bold;
    cursor: pointer;
}

.custom-list-112 {
    list-style: none;
    padding: 0;
    overflow-y: auto;
}


.custom-list-112 .selected {
    background-color: #A3D9FF;
}


.custom-list-112 li {
    background-color: #f5f5f5;
    border: 1px solid #ddd;
    margin: 3px 0;
    padding: 7px;
    border-radius: 4px;
    cursor: pointer;
}
ul::-webkit-scrollbar {
    width: 0; /* 隐藏垂直滚动条 */
}

.custom-list-112 li:hover {
    background-color: #5999FF;
}

.diagnose-icd .el-button{
    margin-left: 60px;
}
</style>
