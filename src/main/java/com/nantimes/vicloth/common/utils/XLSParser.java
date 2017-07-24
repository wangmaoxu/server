package com.nantimes.vicloth.common.utils;

import java.util.ArrayList;
import java.util.List;

import com.nantimes.vicloth.pojo.Hair;
import com.nantimes.vicloth.pojo.Pose;
import com.nantimes.vicloth.pojo.Vicloth;

import jxl.Sheet;
import jxl.Workbook;

public class XLSParser {
	
	public static List<Hair> getAllHairByExcel(String file){

        List<Hair> list=new ArrayList<Hair>();
        try {
            Workbook rwb=Workbook.getWorkbook(new java.io.File(file));
            Sheet rs=rwb.getSheet(0);
            int clos=rs.getColumns();//得到所有的列
            int rows=rs.getRows();//得到所有的行
            
            System.out.println(clos+" rows:"+rows);
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    //第一个是列数，第二个是行数
                    String id=rs.getCell(j++, i).getContents().trim();//默认最左边编号也算一列 所以这里得j++
                    String url=rs.getCell(j++, i).getContents().trim();
                    String hairId=rs.getCell(j++, i).getContents().trim();
                    String color = rs.getCell(j++, i).getContents().trim();
                    String gender = rs.getCell(j++, i).getContents().trim();
                    String priority = rs.getCell(j++, i).getContents().trim();
                    String enable = rs.getCell(j++, i).getContents().trim();
                                 
                    if(id==null)
                    	continue;
                    list.add(new Hair(Integer.parseInt(id),url,hairId,color,gender,Integer.valueOf(priority),enable));
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        return list;
        
    
	}
	
	
    public static List<Pose> getAllPoseByExcel(String file){
        List<Pose> list=new ArrayList<Pose>();
        try {
            Workbook rwb=Workbook.getWorkbook(new java.io.File(file));
            Sheet rs=rwb.getSheet(0);
            int clos=rs.getColumns();//得到所有的列
            int rows=rs.getRows();//得到所有的行
            
            System.out.println(clos+" rows:"+rows);
            for (int i = 1; i < rows; i++) {
            	 String id=rs.getCell(0, i).getContents().trim();
            	 String roleId =rs.getCell(1, i).getContents().trim();
            	 StringBuffer buff = new StringBuffer();
                for (int j = 2; j < clos; j++) {
                    String pose = rs.getCell(j, i).getContents().trim();
                    if(TextUitls.StringNotNull(pose))
                	buff.append(pose)
                	.append(";");        
                    list.add(new Pose(Integer.parseInt(id),roleId,buff.toString()));
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        return list;
        
    }
    
    
    public static List<Vicloth> getAllViclothByExcel(String file){
        List<Vicloth> list=new ArrayList<Vicloth>();
        try {
            Workbook rwb=Workbook.getWorkbook(new java.io.File(file));
            Sheet rs=rwb.getSheet(0);
            int clos=rs.getColumns();//得到所有的列
            int rows=rs.getRows();//得到所有的行
            
            System.out.println(clos+" rows:"+rows);
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    //第一个是列数，第二个是行数
                    String id=rs.getCell(j++, i).getContents().trim();//默认最左边编号也算一列 所以这里得j++
                    String subject=rs.getCell(j++, i).getContents().trim();
                    String url=rs.getCell(j++, i).getContents().trim();
                    String role=rs.getCell(j++, i).getContents().trim();
                    String priority = rs.getCell(j++, i).getContents().trim();
                    String roleId = rs.getCell(j++, i).getContents().trim();
                    String themeName = rs.getCell(j++, i).getContents().trim();
                    String color = rs.getCell(j++, i).getContents().trim();
                    String gender = rs.getCell(j++, i).getContents().trim();
                    String tmp = rs.getCell(j++, i).getContents().trim();
                    int assort=0;
                    if(TextUitls.StringNotNull(tmp))
                    assort= Integer.valueOf(tmp);
                    String enable = rs.getCell(j++, i).getContents().trim();
                    String hat = rs.getCell(j++, i).getContents().trim();
                                 
                    if(id==null)
                    	continue;
                    list.add(new Vicloth(Integer.parseInt(id), subject, url, role, Integer.valueOf(priority), roleId
                    		, themeName,color,gender,assort,enable,hat));
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        return list;
        
    }

}
