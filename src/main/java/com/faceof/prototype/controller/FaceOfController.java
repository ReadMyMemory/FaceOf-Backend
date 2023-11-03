package com.faceof.prototype.controller;


import com.faceof.prototype.service.FaceOfService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.InputStreamReader;



@Controller                 // 이 프로젝트에서 컨트롤러는 서비스에 있는 메소드만 호출, 트랜잭션 스크립트 패턴
@RequiredArgsConstructor    // final 붙은 애들에 한에서 생성자를 만들어줌
public class FaceOfController {

    /*
     주로 생성자 주입을 많이 쓰고 final을 붙여서 내용을 변경할 수 없게함
     여기서 내용을 변경한다는 것은 서비스, 컨트롤러의 연결 관계인 의존성을 변경해버리겠다는 건데
     그런걸 구현할 필요도 없고 실제로 절대 런타임중에 변경될 일이 없다고 함
     현재 프로젝트에서는 컨트롤러 하나 서비스 하나 밖에 없어서 변경될 연결 관계도 없음
     여튼 생성자 의존성 주입 방식을 사용할 거고 FaceOfController -> FaceOfService가 완성됨
    */
    
    private final FaceOfService faceOfService;

    @PostMapping("/upload")
    public String extractData(@RequestParam("img") MultipartFile img){

        // 만약 사용자가 아무런 이미지도 업로드하지 않고 전송한 경우 처리
        // 이 코드는 나중에 프론트에서 이미지파일이 업로드 되지 않으면 제출 버튼을 비활성하게 가능하다고 해서 삭제 될

        try {
            // 스프링 부트에서 파이썬 스크립트 실행시킴
            String scriptPath = "실행시킬 .py 파일의 경로";
            ProcessBuilder processBuilder = new ProcessBuilder("python", scriptPath);
            Process process = processBuilder.start();


            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // 프로세스 종료 대기
            int exitCode = process.waitFor();
            System.out.println("Python 스크립트 실행이 완료되었습니다. 종료 코드: " + exitCode);


        } catch (Exception e) {
            // 이미지 처리 중 에러 발생 시 예외 처리
            // 사실 딱히 처리 한건 없고 그냥 처음 index.html로 돌려 보낸것 밖에 없음
            return "redirect:";
        }
        
        // 정상처리 되면 result.html 로 화면 이동, 모델을 이용해서 나중에 result로 가면서 들고 갈 예정
        // 정상처리시 파이썬 스크립트에서 분석한 결과를 알고리즘에 넣고
        // 최종 결과를 들고 result로 가서 사용자가 볼 수 있게 화면을 띄워줘야 함
        return "result";
    }
}