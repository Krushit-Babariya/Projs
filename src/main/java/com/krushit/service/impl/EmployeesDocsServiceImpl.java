package com.nj.service.impl;

import com.nj.dto.EmployeeDocsDTO;
import com.nj.entity.EmployeeDocs;
import com.nj.repository.EmployeesDocsRepository;
import com.nj.service.IEmployeesDocsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeesDocsServiceImpl implements IEmployeesDocsService {

    @Autowired
    private EmployeesDocsRepository repository;

    private final String UPLOAD_DIR = "/opt/apache-tomcat-10.1.36/webapps/nj_hr/employee_docs/";
    private final String DIR = "/opt/apache-tomcat-10.1.36/webapps/nj_hr/";

    @Override
    public void saveEmployeeDocs(EmployeeDocsDTO dto) {
        try {
            String photoFilename = saveFile(dto.getPhotoPath(), dto.getEmployeeId());
            String resumeFilename = saveFile(dto.getResumePath(), dto.getEmployeeId());
//            EmployeeDocs existingDocs = repository.getEmployeeDocsByEmployeeId(dto.getEmployeeId()).get(0);
            EmployeeDocs existingDocs = repository.getEmployeeDocsByEmployeeId(dto.getEmployeeId()).isEmpty() ? null : repository.getEmployeeDocsByEmployeeId(dto.getEmployeeId()).get(0);
            if (existingDocs != null) {
                softDeleteFile(existingDocs.getPhotoPath());
                softDeleteFile(existingDocs.getResumePath());
                existingDocs.setPhotoPath(photoFilename);
                existingDocs.setResumePath(resumeFilename);
                repository.updateEmployeeDocs(existingDocs);
            } else {
                EmployeeDocs newDocs = new EmployeeDocs(0, photoFilename, resumeFilename, dto.getEmployeeId());
                repository.addEmployeeDocs(newDocs);
            }
        } catch (IOException e) {
            throw new RuntimeException("File upload failed", e);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to save employee documents", ex);
        }
    }

    private void softDeleteFile(String path) {
        if (path == null || path.isEmpty()) {
            return;
        }
        path = UPLOAD_DIR + path;
        File file = new File(path);
        if (file.exists()) {
            File renamed = new File(file.getParent(), "DEL_" + file.getName());
            if (!file.renameTo(renamed)) {
                System.err.println("Failed to soft delete file: " + file.getAbsolutePath());
            }
        }
    }

    private String saveFile(MultipartFile file, int employeeId) throws IOException {
        if (file == null || file.isEmpty()) {
            return null;
        }

        File dir = new File(UPLOAD_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String uniqueName = String.valueOf(employeeId) + "_" + System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File dest = new File(dir, uniqueName);

        file.transferTo(dest);

        return "/employee_docs/" + uniqueName; // assuming you expose it via a static mapping
//        return uniqueName;
    }

    @Override
    public void deleteEmployeeDocs(int id) {
        repository.deleteEmployeeDocs(id);
    }

    @Override
    public List<EmployeeDocs> getDocsByEmployeeId(int employeeId) {
        return repository.getEmployeeDocsByEmployeeId(employeeId);
    }

    @Override
    public EmployeeDocs getDocById(int id) {
        return repository.getEmployeeDocsById(id);
    }

    @Override
    public String fetchResumePathByJsId(int id) {
        return DIR + repository.getResumePathByEmployeeId(id);
    }

    @Override
    public String fetchPhotoPathByJsId(int id) {
        return DIR + repository.getPhotoPathByEmployeeId(id);
    }

}
