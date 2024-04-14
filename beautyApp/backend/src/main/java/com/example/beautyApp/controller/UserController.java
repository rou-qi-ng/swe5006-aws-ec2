package com.example.beautyApp.controller;

import com.example.beautyApp.manager.ReferralManager;
import com.example.beautyApp.manager.ServiceManager;
import com.example.beautyApp.manager.UserManager;
import com.example.beautyApp.model.TB_Service;
import com.example.beautyApp.model.TB_User;
import com.example.beautyApp.model.TB_UserSession;
import com.example.beautyApp.repository.ReferralRepository;
import com.example.beautyApp.repository.UserRepository;
import com.example.beautyApp.request.LoginRequest;
import com.example.beautyApp.request.RoleRequest;
import com.example.beautyApp.request.SessionRequest;
import com.example.beautyApp.request.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserManager userManager;
    @Autowired
    private ServiceManager serviceManager;

    @Autowired
    private ReferralManager referralManager;

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws Exception {
        Optional<TB_User> user = userManager.login(loginRequest);
        System.out.println(user);
        String uuid = UUID.randomUUID().toString();

        String timeStamp = uuid + new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        if (user.isEmpty()){

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of(
                    "statusCode", "200",
                    "message", "No existing functions"
            ));
        } else{
            return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                    "token", timeStamp
            ));
        }

    }

    @PostMapping(path = "/saveSession")
    public ResponseEntity<?> saveSession(@RequestBody SessionRequest sessionRequest) throws Exception {
        System.out.println("hello");
        Optional<TB_UserSession> user = userManager.saveSession(sessionRequest);
        System.out.println(user);
        System.out.println("hello");
        if (user.isEmpty()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of(
                    "statusCode", "200",
                    "message", "No existing functions"
            ));
        } else{
            return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                    "statusCode", "200",
                    "message", "Success"
            ));
        }

    }

    @PostMapping(path = "/register")
    public ResponseEntity<?> register(@RequestBody SignUpRequest loginRequest) throws Exception {
        Optional<TB_User> user = userManager.register(loginRequest);
        System.out.println(user);

        if (user.isEmpty()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of(
                    "statusCode", "200",
                    "message", "No existing functions"
            ));
        } else{
            return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                    "statusCode", "200",
                    "message", "Success"
            ));
        }

    }



    @GetMapping(path = "/login")
    public Collection<?> login2() throws Exception {
        Collection<TB_User> user = userManager.login2();
        System.out.println(user);

        return user;
    }

    @PostMapping(path = "/findRole")
    public String findRole(@RequestBody RoleRequest roleRequest) throws Exception {
        System.out.println("----LOG: START FINDROLE------");
        String user = userManager.findRole(roleRequest.getToken());
//        System.out.println(user);
        System.out.println(user);
        System.out.println("----LOG: END FINDROLE------");
        return user;
    }

    @GetMapping(path = "/getReferralCode")
    public ResponseEntity<?> getReferralCode() throws Exception {

        String code = referralManager.getCode("test");
        System.out.println(code);

        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "statusCode", "200",
                "message", "Success",
                "data", code
        ));
//        return code;
    }


    @PostMapping(path = "/registerService")
    public ResponseEntity<?> registerService() throws Exception {
        TB_Service service = serviceManager.store(null);
        System.out.println(service);

        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "statusCode", "200",
                "message", "Success"
        ));


    }

    @GetMapping("/getUserId")
    public ResponseEntity<Optional<Integer>> getUserSessionByToken(@RequestParam("token") String token) {
        Optional<Integer> userSession = userManager.getUserSessionByToken(token);
        if (userSession.isPresent()) {
            return ResponseEntity.ok(userSession);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getUsername")
    public ResponseEntity<Optional<String>> getUsernameById(@RequestParam("userId") int id) {
        Optional<String> username = userManager.getUsernameById(id);
        if (username.isPresent()) {
            return ResponseEntity.ok(username);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    public TB_Service store(MultipartFile file) throws IOException {
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//        TB_Service newFile = new TB_Service(1, fileName, "", "",file.getContentType(), file.getBytes());
//
//        return serviceRepository.save(newFile);
//    }

}
