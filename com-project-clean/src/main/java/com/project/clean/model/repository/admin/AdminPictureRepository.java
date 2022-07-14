package com.project.clean.model.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.clean.model.domain.commonEntity.AdminPicture;

public interface AdminPictureRepository extends JpaRepository<AdminPicture, Integer>{

	AdminPicture findByAdminNo(int adminNo);

}
