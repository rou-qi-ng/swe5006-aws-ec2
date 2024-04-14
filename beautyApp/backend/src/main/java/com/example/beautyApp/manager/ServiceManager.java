package com.example.beautyApp.manager;

import com.example.beautyApp.model.TB_Service;
import com.example.beautyApp.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class ServiceManager {

    @Autowired
    private ServiceRepository serviceRepository;

    public TB_Service store(MultipartFile file) throws IOException {
        if (file != null){
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            TB_Service newFile = new TB_Service(1, fileName, "", "",file.getContentType(), file.getBytes());

            return serviceRepository.save(newFile);
        }
        return null;

    }

    public TB_Service getFile(Integer id) {
        return serviceRepository.findByServiceId(id) ;
    }

    public Stream<TB_Service> getAllFiles() {
        return serviceRepository.findAll().stream();
    }
}