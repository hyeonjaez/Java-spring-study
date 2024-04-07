package com.nhnacademy.jaehyeon.exercise10_6;


import java.io.FileNotFoundException;

/*
 * https://math.hws.edu/javanotes/c10/exercises.html
 * 주어진 입력 파일에서 문서를 읽어와, 각 단어와 해당 단어가 나타난 줄 번호를 포함하는 색인을 생성하는 프로그램을 작성합니다.
 * 줄 번호를 추적하면서 문서를 읽고, 줄 끝 문자('\n')을 기준으로 줄 번호를 증가시킵니다.
 * "the"와 길이가 3 미만인 단어를 용어집에서 제외하고, 결과를 출력 파일에 저장합니다. TextIO를 사용하여 파일을 처리하며, 입력 파일과 출력 파일은 프로그램 실행 시 사용자가 선택할 수 있어야 합니다.
 */
public class Exercise10_6 {
    public static void main(String[] args) {
        try {
            FileSelector fileSelector = new FileSelector();
            String readFilePath = fileSelector.selectReadFile();

            FileRead fileRead = new FileRead(readFilePath);
            fileRead.readFileOneLine();

            String writeFilePath = fileSelector.selectWriteFile();
            FileWrite fileWrite = new FileWrite(writeFilePath);

            fileWrite.writeFile(fileRead.getWordList());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        System.exit(0);
    }
}
