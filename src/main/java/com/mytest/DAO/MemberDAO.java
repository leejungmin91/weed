package com.mytest.DAO;

import java.util.ArrayList;


import com.mytest.DTO.MemberDTO;

public interface MemberDAO {
	
	public MemberDTO getMemberName(String name);
	public String getMemberFb_id(String fb_id);

	public void insertMember(MemberDTO member);

	public void updateMember(String name);

	public void deleteMember(String name);
}