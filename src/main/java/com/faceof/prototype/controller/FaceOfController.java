package com.faceof.prototype.controller;


import com.faceof.prototype.service.FaceOfService;
import org.opencv.core.MatOfByte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.opencv.core.Mat;
import org.opencv.core.CvType;
import org.opencv.imgcodecs.Imgcodecs;
import java.io.IOException;


// 원래는 컨트롤러도 인터페이스를 만들고 그걸 구현해야 하는걸로 알지만 애자일하게 생략함


// @Controller 안에 스프링 @Component가 있어서 스프링 빈 자동 등록
// Component 스캔과 Autowired를 통해서 컨트롤러와 서비스, 서비스와 레포지토리를 연결해 나갈 수 있음

// 지금은 작은 프로젝트라 상관없지만 나중에 컨트롤러가 여러개가 되는 상황이 있을 수 있음
// 그렇게 되면 FaceOfController에서만 FaceOfService에 접근해야 하는데 다른 컨트롤러에서 FaceOfService s = new FaceOfService();
// 이렇게 해서 접근 할 수 있게됨
// 서로 연결될 관계를 설정해주고, 다른 애들은 쓰지 못하게 하려고 의존성 자동주입을 해주는 @Autowired를 사용할 거임


@Controller
public class FaceOfController {

    // 주로 생성자 주입을 많이 쓰고 final을 붙여서 내용을 변경할 수 없게함
    // 여기서 내용을 변경한다는 것은 서비스, 컨트롤러의 연결 관계인 의존성을 변경해버리겠다는 건데
    // 그런걸 구현할 필요도 없고 실제로 절대 런타임중에 변경될 일이 없다고 함



    // FaceOfController는 FaceOfService가 필요함
    private final FaceOfService faceOfService;

    // 생성자 의존성 주입 방식을 사용할거임
    @Autowired
    public FaceOfController(FaceOfService faceOfService){
        this.faceOfService = faceOfService;
    }

    // 이렇게 해주면 FaceOfController -> FaceOfService가 완성됨



    // 사용자가 index.html에서 사진 이미지를 선택해서 업로드 한다면
    @PostMapping("/upload")
    public String extractData(@RequestParam("img") MultipartFile img){

        // 만약 사용자가 아무런 이미지도 업로드하지 않고 전송한 경우 처리
        // 반드시 사진을 업로드 하라고 메세지가 추가 되야 할듯
        if(img.isEmpty()) return "redirect:";

        // 업로드된 이미지 처리 로직이 여기에 들어감


        try {
            // MultipartFile에서 이미지 데이터를 읽어와서 OpenCV로 처리라기
            // index.html에서 사용자가 올린 이미지 파일 img를  가져와서 OpenCV의 MAT 객체로 바꿔줌, 이래야 OpenCV에서 이미지를 활용할 수 있나봄
            // OpenCV관련 자세한 코드는 구글링 해서 사용

            Mat matImage = Imgcodecs.imdecode(new MatOfByte(img.getBytes()), Imgcodecs.IMREAD_UNCHANGED);

            // 가상머신 안에 있는 파이썬 파일에 저 데이터를 그대로 넘겨줌
            // 그럼 가상머신 안에서 추출해서 가져옴

            // 그후 그 결과를 반환해줌



            
        } catch (IOException e) {
            // 이미지 처리 중 에러 발생 시 예외 처리
            return "redirect:";
        }
        
        // 정상처리 되면 result.html 로 화면 이동, 모델을 이용해서 나중에 result로 가면서 들고 갈 예정
        return "result";
    }
}