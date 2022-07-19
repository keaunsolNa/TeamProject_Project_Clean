package com.project.clean.model.service.vacation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.clean.model.domain.adminEntity.AdminVacationCommit;
import com.project.clean.model.domain.commonEntity.Vacation;
import com.project.clean.model.dto.commonDTO.VacationDTO;
import com.project.clean.model.dto.joinDTO.AdminVacationCommitDTO;
import com.project.clean.model.repository.vacation.AdminVacationCommitRepository;
import com.project.clean.model.repository.vacation.VacationRepository;

@Service
public class VacationService {

	private final VacationRepository vacationRepository;
	private final ModelMapper modelMapper;
	private final AdminVacationCommitRepository adminVacationCommitRepository;
	
	@Autowired
	public VacationService(VacationRepository vacationRepository, ModelMapper modelMapper, AdminVacationCommitRepository adminVacationCommitRepository) {
		this.vacationRepository = vacationRepository;
		this.adminVacationCommitRepository = adminVacationCommitRepository;
		this.modelMapper = modelMapper;
	}
	
	/* 휴가 1차 승인 대기목록 조회 */
	public List<VacationDTO> findVacationFirstList() {
		
		/* 1차 승인 여부가 N인 목록 조회 */
		List<Vacation> vacationList = vacationRepository.findVacationByFirstConfrimN();
		
		return vacationList.stream().map(vacation -> modelMapper.map(vacation, VacationDTO.class)).collect(Collectors.toList());
	}

	/* 휴가 2차 승인 대기 목록 조회 */
	public List<VacationDTO> findVacationSecondList() {
		
		/* 1차 승인여부가 Y이며 2차 승인 여부가 N이고 반려 여부가 N인 목록 조회 */ 
		List<Vacation> vacationList = vacationRepository.findVacationBySecondConfirmN();
		
		return vacationList.stream().map(vacation -> modelMapper.map(vacation, VacationDTO.class)).collect(Collectors.toList());
	}

	/* 휴가 정보 상세 조회 */
//	public VacationDTO findByVacationNo(int vacationNo) {
//		
//		Vacation vacation = vacationRepository.findByVacationNo(vacationNo);
//		
//		return modelMapper.map(vacation, VacationDTO.class);
//	}
//
//	public List<VacationDTO> findbyCommitAdminList(int vacationNo) {
//		
//		List<Vacation> commitAdminList = vacationRepository.findVacationCommitAdminList(vacationNo);
//		
//		return commitAdminList.stream().map(vacation -> modelMapper.map(vacation, VacationDTO.class)).collect(Collectors.toList());
//	}

	/* 휴가 상세 조회 */
	public AdminVacationCommitDTO findByVacationNo(int vacationNo) {

		AdminVacationCommit vacationList = adminVacationCommitRepository.findByVacationNo(vacationNo);
		
		return modelMapper.map(vacationList, AdminVacationCommitDTO.class);
	}

	/* 휴가 신청 */
	public void registNewVacationApply(VacationDTO vacation) {
		
		vacationRepository.save(modelMapper.map(vacation, Vacation.class));
		
		
	}

	

}
