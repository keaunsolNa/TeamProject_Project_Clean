package com.project.clean.model.service.vacation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.clean.model.domain.commonEntity.Vacation;
import com.project.clean.model.domain.commonEntity.VacationCommit;
import com.project.clean.model.dto.commonDTO.VacationCommitDTO;
import com.project.clean.model.dto.commonDTO.VacationDTO;
import com.project.clean.model.repository.vacation.AdminVacationCommitRepository;
import com.project.clean.model.repository.vacation.AdminVacationRepository;
import com.project.clean.model.repository.vacation.VacationCommitRepository;
import com.project.clean.model.repository.vacation.VacationRepository;

@Service
public class VacationService {

	private final VacationRepository vacationRepository;
	private final ModelMapper modelMapper;
	private final AdminVacationCommitRepository adminVacationCommitRepository;
	private final VacationCommitRepository vacationCommitRepository;
	private final AdminVacationRepository adminVacationRepository;
	
	@Autowired
	public VacationService(VacationRepository vacationRepository, ModelMapper modelMapper, AdminVacationCommitRepository adminVacationCommitRepository
			, VacationCommitRepository vacationCommitRepository, AdminVacationRepository adminVacationRepository) {
		this.vacationRepository = vacationRepository;
		this.adminVacationCommitRepository = adminVacationCommitRepository;
		this.modelMapper = modelMapper;
		this.vacationCommitRepository = vacationCommitRepository;
		this.adminVacationRepository = adminVacationRepository;
	}
	
	/* 휴가 1차 승인 대기목록 조회 */
	@Transactional
	public List<VacationDTO> findByVacationList() {
	
	/* 1차 승인 여부가 N인 목록 조회 */
	 List<Vacation> vacationList = vacationRepository.findVacationByFirstConfrimN();

	return vacationList.stream().map(vacation -> modelMapper.map(vacation, VacationDTO.class)).collect(Collectors.toList());
}


	/* 휴가 2차 승인 대기 목록 조회 */
	@Transactional
	public List<VacationDTO> findVacationSecondList() {
		
		/* 1차 승인여부가 Y이며 2차 승인 여부가 N이고 반려 여부가 N인 목록 조회 */ 
		List<Vacation> vacationList = vacationRepository.findVacationBySecondConfirmN();
		
		return vacationList.stream().map(vacation -> modelMapper.map(vacation, VacationDTO.class)).collect(Collectors.toList());
	}

	/* 휴가 정보 상세 조회 */
	public VacationDTO findByVacationNo(int vacationNo) {
		
		Vacation vacation = vacationRepository.findByVacationNo(vacationNo);
		
		return modelMapper.map(vacation, VacationDTO.class);
	}

//	public List<VacationDTO> findbyCommitAdminList(int vacationNo) {
//		
//		List<Vacation> commitAdminList = vacationRepository.findVacationCommitAdminList(vacationNo);
//		
//		return commitAdminList.stream().map(vacation -> modelMapper.map(vacation, VacationDTO.class)).collect(Collectors.toList());
//	}

	/* 휴가 상세 조회 */
//	public VacationDTO findByVacationNo(int vacationNo) {
//
//		Vacation vacationList = vacationRepository.findByVacationNo(vacationNo);
//		
//		return modelMapper.map(vacationList, VacationDTO.class);
//	}

	/* 휴가 신청 */
	public void registNewVacationApply(VacationDTO vacation) {
		
		vacationRepository.save(modelMapper.map(vacation, Vacation.class));
		
		
	}

	/* 결재내역 insert */
	@Transactional
	public void registVacationCommit(VacationCommitDTO vacationCommit) {
		
		vacationCommitRepository.save(modelMapper.map(vacationCommit, VacationCommit.class));
		
	}

	/* 1차 승인에 따른 승인여부 update */
	@Transactional
	public void modifyFirstConfirmYn(int vacationNo) {
		
		vacationRepository.findByVacationFirstConfirm(vacationNo);
		
	}

	/* 2차 승인에 따른 승인 여부 update */
	@Transactional
	public void modifySecondConfirmYn(int vacationNo) {
		
		vacationRepository.findByVacationSecondConfirm(vacationNo);
		
	}

	/* 반려 여부 update */
	@Transactional
	public void modifyReturnYn(int vacationNo) {
		
		vacationRepository.findByVacationReturnYn(vacationNo);
		
	}

	/* 휴가 승인을 위한 상세 조회 */
	public VacationDTO findAdminByVacationNo(int vacationNo) {

		Vacation vacationList = vacationRepository.findByVacationNo(vacationNo);

		return modelMapper.map(vacationList, VacationDTO.class);
		
	}
	

	

	

}
