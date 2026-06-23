#!/usr/bin/env bash
set -euo pipefail

# 在仓库根目录执行：./git-commit-push.sh "提交说明"
# 未传参数时使用默认说明

REPO_ROOT="$(cd "$(dirname "$0")" && pwd)"
cd "$REPO_ROOT"

MSG="${1:-chore: 更新}"

git add -A
if git diff --staged --quiet; then
  echo "没有要提交的变更"
  exit 0
fi

git commit -m "$MSG"
git push origin "$(git rev-parse --abbrev-ref HEAD)"
