#!/bin/bash

CUR_DIR=$(pwd | grep -o '[^/]*$')

if [[ $CUR_DIR != "icon" ]]
then
  if [ -d icon ]
  then
    cd icon
  else
    echo "[Error] 올바른 스크립트 실행 경로가 아닙니다 프로젝트 루트나 icon 디렉토리에서 bash 로 실행시켜 주세요"
    exit 1
  fi
fi

MODULE_NAME=$1
if [[ $MODULE_NAME = "" ]]
then
  MODULE_NAME="app"
fi
echo "[Info] 모듈 이름은 $MODULE_NAME 입니다"
MODULE_PATH="../$MODULE_NAME"

PATH_MDPI=$MODULE_PATH
PATH_MDPI+='/src/main/res/drawable'
PATH_HDPI=$MODULE_PATH
PATH_HDPI+='/src/main/res/drawable-hdpi'
PATH_XHDPI=$MODULE_PATH
PATH_XHDPI+='/src/main/res/drawable-xhdpi'
PATH_XXHDPI=$MODULE_PATH
PATH_XXHDPI+='/src/main/res/drawable-xxhdpi'
PATH_XXXHDPI=$MODULE_PATH
PATH_XXXHDPI+='/src/main/res/drawable-xxxhdpi'

declare -a MDPI_LIST
declare -a HDPI_LIST
declare -a XHDPI_LIST
declare -a XXHDPI_LIST
declare -a XXXHDPI_LIST

# Create directories if not exists
[ ! -d $PATH_MDPI ] && mkdir -p $PATH_MDPI >/dev/null 2>&1
[ ! -d $PATH_HDPI ] && mkdir -p $PATH_HDPI >/dev/null 2>&1
[ ! -d $PATH_XHDPI ] && mkdir -p $PATH_XHDPI >/dev/null 2>&1
[ ! -d $PATH_XXHDPI ] && mkdir -p $PATH_XXHDPI >/dev/null 2>&1
[ ! -d $PATH_XXXHDPI ] && mkdir -p $PATH_XXXHDPI >/dev/null 2>&1

VALID_ASSET_REGEX="^[a-z_]+(@1.5x|@2x|@3x|@4x)*\.png$"

HDPI_REGEX="@1.5x"
XHDPI_REGEX="@2x"
XXHDPI_REGEX="@3x"
XXXHDPI_REGEX="@4x"

FAILED=false

for f in *.png
do
  if [[ $f = "*.png" ]]
  then
    continue
  fi


  if [[ $f =~ $VALID_ASSET_REGEX ]]
  then
    if [[ $f =~ $XXXHDPI_REGEX ]]
    then
      XXXHDPI_LIST+=($f)
    elif [[ $f =~ $XXHDPI_REGEX ]]
    then
      XXHDPI_LIST+=($f)
    elif [[ $f =~ $XHDPI_REGEX ]]
    then
      XHDPI_LIST+=($f)
    elif [[ $f =~ $HDPI_REGEX ]]
    then
      HDPI_LIST+=($f)
    else
      MDPI_LIST+=($f)
    fi
  else # Failed
    echo "[Error] $f 는 올바른 이미지 이름이 아닙니다."
    FAILED=true
  fi
done

if [ $FAILED = true ]
then
  exit 1
fi


# Uncomment if need strictly check 5 screen density qualifiers condition

#LEN_MDPI=${#MDPI_LIST[@]}
#LEN_HDPI=${#HDPI_LIST[@]}
#LEN_XHDPI=${#XHDPI_LIST[@]}
#LEN_XXHDPI=${#XXHDPI_LIST[@]}
#LEN_XXXHDPI=${#XXXHDPI_LIST[@]}
#
#if [[ $LEN_MDPI != $LEN_HDPI ]] || [[ $LEN_MDPI != $LEN_XHDPI ]] || [[ $LEN_MDPI != $LEN_XXHDPI ]] || [[ $LEN_MDPI != $LEN_XXXHDPI ]]
#then
#  echo "[Error] 어떤 아이콘이 icon 디렉토리에 5개가 준비되어있지 않습니다."
#  exit 1
#fi


for f in "${MDPI_LIST[@]}"
do
  TMP=$PATH_MDPI
  TMP+="/"
  TMP+=$f
  echo $TMP
  mv $f $PATH_MDPI
done

for f in "${HDPI_LIST[@]}"
do
  SUFFIX="@1.5x.png"
  DEST_FILE_NAME=${f%"$SUFFIX"}
  DEST_FILE_NAME+=".png"

  DEST_PATH=$PATH_HDPI
  DEST_PATH+="/"
  DEST_PATH+=$DEST_FILE_NAME

  echo $DEST_PATH
  mv $f $DEST_PATH
done

for f in "${XHDPI_LIST[@]}"
do
  SUFFIX="@2x.png"
  DEST_FILE_NAME=${f%"$SUFFIX"}
  DEST_FILE_NAME+=".png"

  DEST_PATH=$PATH_XHDPI
  DEST_PATH+="/"
  DEST_PATH+=$DEST_FILE_NAME

  echo $DEST_PATH
  mv $f $DEST_PATH
done

for f in "${XXHDPI_LIST[@]}"
do
  SUFFIX="@3x.png"
  DEST_FILE_NAME=${f%"$SUFFIX"}
  DEST_FILE_NAME+=".png"

  DEST_PATH=$PATH_XXHDPI
  DEST_PATH+="/"
  DEST_PATH+=$DEST_FILE_NAME

  echo $DEST_PATH
  mv $f $DEST_PATH
done

for f in "${XXXHDPI_LIST[@]}"
do
  SUFFIX="@4x.png"
  DEST_FILE_NAME=${f%"$SUFFIX"}
  DEST_FILE_NAME+=".png"

  DEST_PATH=$PATH_XXXHDPI
  DEST_PATH+="/"
  DEST_PATH+=$DEST_FILE_NAME

  echo $DEST_PATH
  mv $f $DEST_PATH
done
