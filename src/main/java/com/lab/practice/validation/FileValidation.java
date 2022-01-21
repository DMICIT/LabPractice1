package com.lab.practice.validation;

import com.lab.practice.entity.FileBucket;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileValidation implements Validator {

    @Override
    public void validate (Object uploadedFile, Errors errors) {

        FileBucket file = (FileBucket) uploadedFile;
        MultipartFile multipartFile = file.getFile();
        if(multipartFile.isEmpty() || multipartFile.getSize()==0){
            errors.rejectValue("file", "Please select a file");
        }
        if(!multipartFile.getContentType().endsWith("csv")){
            errors.rejectValue("file", "Please select a csv file");
        }
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return MultipartFile.class.isAssignableFrom(clazz);
    }

}
