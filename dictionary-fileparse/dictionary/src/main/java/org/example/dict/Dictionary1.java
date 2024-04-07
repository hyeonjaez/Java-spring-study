
package org.example.dict;


import java.io.File;
import java.util.Collection;
import java.util.List;

/**
 * CSV 사전.
 * <pre>
 * ===========================================================
 * DATE             AUTHOR               NOTE
 * -----------------------------------------------------------
 * 2023/09/21       김현준                 최초 생성
 * </pre>
 *
 * @author 김현준
 * @since 2023/09/21
 */
public interface Dictionary1 {

    /**
     * 파일을 읽어 메모리에 적재한다.
     *
     * @param path 파일 경로
     * @author 김현준
     */

    void load(File file);

    /**
     * 한글을 입력받아 영어단어 리스트를 돌려준다.
     *
     * @param kor 한글.
     * @return 영단어 리스트
     * @author 김현준
     */
    List<String> findEngByKor(String kor);

    /**
     * 한글 단어가 몇개나 있는지 확인한다.
     * <br>
     * 동음이의어, 동의어는 1개로 간주한다. (중복 X)
     *
     * @return int
     * @author 김현준
     */
    int count();

    /**
     * 모든 한글 단어 목록을 가져온다.
     *
     * @return 한글 단어 콜렉션 // TODO 적절한 자료구조로 리턴 타입 변경
     * @author 김현준
     */
    Collection<String> findAllListKor();

    List<String> findAllEngByKorOrderByHomonymCountDescAndKorDesc();
}
