#!/bin/sh

# 현재 폴더와 하위 폴더에서 PNG 파일 찾아서 처리
find . -type f -name "*.png" | while read -r file; do
  # 공백을 언더바(_)로 변경한 새 파일 이름 생성
  new_name=$(basename "$file" | tr ' ' '_')
  new_name="icon_$new_name"

  # 파일 이동 및 이름 변경
  mv "$file" "$new_name"
  echo "이동 및 이름 변경: $file -> $new_name"
done

# 모든 폴더 삭제
find . -type d -exec rm -rf {} \;

# 작업이 완료되었음을 알림
echo "작업 완료"