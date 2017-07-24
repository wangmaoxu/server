package com.nantimes.vicloth.serviceImpl;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nantimes.vicloth.common.utils.CmdExec;
import com.nantimes.vicloth.common.utils.TextUitls;
import com.nantimes.vicloth.mapping.BodyMapper;
import com.nantimes.vicloth.mapping.PoseMapper;
import com.nantimes.vicloth.mapping.UserMapper;
import com.nantimes.vicloth.model.VResponse;
import com.nantimes.vicloth.model.vo.RenderView;
import com.nantimes.vicloth.pojo.Body;
import com.nantimes.vicloth.pojo.Pose;
import com.nantimes.vicloth.pojo.User;
import com.nantimes.vicloth.service.IRenderService;


@Service("RenderService")
public class RenderServiceImpl implements IRenderService {
	private static final String sRenderAllCmd = "/home/wmx/asset/all.sh";
	private static final String sRenaderAngeCmd = "/home/wmx/asset/angle.sh";
	private static final String sRenaderFaceBodyCmd = "/home/wmx/asset/body_face.sh";
	private static final String urlAllFormat="http://123.56.19.60:8080/cloth/render/%1$s_%2$s_%3$s_%4$s_%5$s_%6$s_quan_%7$s.png";
	private static final String urlAngleFormat="http://123.56.19.60:8080/cloth/render/%1$s_%2$s_%3$s_%4$s_%5$s_%6$s.png" ;  
	private static final String MALE="1",FEMALE="0";

	private static final String MALE_HAIR_DEFAULT = "50304";
	private static final String FEMALE_HAIR_DEFAULT ="60007";
	
	@Resource
	BodyMapper _bodyMapper;
	
	@Resource UserMapper _userMapper;
	
	@Resource PoseMapper _poseMapper;



	@Override
	public RenderView renderByPose(int id, String roleId) {
		
		return pose(id, roleId);
	}

	@Override
	public RenderView renderFullAngle(int id, String roleId,String motionId) {
		
		return fullAngle(id,roleId,motionId);
	}

	@Override
	public RenderView renderByPoseWithUpdateHair(int id, String roleId, String hair) {
		_bodyMapper.updateHair(id, hair);
		
		return pose(id, roleId);
	}


	@Override
	public RenderView renderFullAngleWithUpdateHair(int id, String roleId, String motion, String hairId) {
		_bodyMapper.updateHair(id, hairId);
		return fullAngle(id,roleId,motion);
	}


	@Override
	public VResponse renderFaceBody(String user, String _hight, String _waist, String gender, String hairId) {
		String[] args = new String[6];
		args[0]=sRenaderFaceBodyCmd;
		args[1] = user;
		args[2] = _hight;
		args[3] = _waist;
		args[4] = hairId;
		args[5] = translateGender(gender);
		VResponse vResponse = new VResponse();
		try{
			CmdExec.doExec(args);
		
		}catch(Exception e){
			vResponse.setStatusCode("400");
		}
		CmdExec.doExec(args);
		return  vResponse;
	}

	private String translateGender(String gender){
		if(gender.equals("male")){
			return MALE;
		}
		return FEMALE;
	}
	
	float getfitwidth(float inheight, float inwidth,int bman)
	{
		float widthlist[]= new float[21];

		float heigthline = 0;

		if (bman == 1)
		{
			heigthline = 176.0f;
			widthlist[0] = 70.0f; widthlist[1] = 72.0f; widthlist[2] = 74.0f;
			widthlist[3] = 76.0f; widthlist[4] = 78.0f; widthlist[5] = 80.0f;
			widthlist[6] = 82.0f; widthlist[7] = 84.0f; widthlist[8] = 86.0f;
			widthlist[9] = 88.0f; widthlist[10] = 90.0f; widthlist[11] = 92.0f;
			widthlist[12] = 94.0f; widthlist[13] = 96.0f; widthlist[14] = 98.0f;
			widthlist[15] = 100.0f; widthlist[16] = 102f; widthlist[17] = 104.0f;
			widthlist[18] = 106.0f; widthlist[19] = 108.0f; widthlist[20] = 110.0f;
		}
		else
		{
			heigthline = 166.0f;
			widthlist[0] = 50.0f; widthlist[1] = 52.0f; widthlist[2] = 54.0f;
			widthlist[3] = 56.0f; widthlist[4] = 58.0f; widthlist[5] = 60.0f;
			widthlist[6] = 62.0f; widthlist[7] = 64.0f; widthlist[8] = 66.0f;
			widthlist[9] = 68.0f; widthlist[10] = 70.0f; widthlist[11] = 72.0f;
			widthlist[12] = 74.0f; widthlist[13] = 76.0f; widthlist[14] = 78.0f;
			widthlist[15] = 80.0f; widthlist[16] = 82f; widthlist[17] = 84.0f;
			widthlist[18] = 86.0f; widthlist[19] = 88.0f; widthlist[20] = 90.0f;
		}

		
		

		float out = (heigthline * inwidth) / inheight;
		int index = 0;
		for (; index < 21; index++)
		{
			if (widthlist[index] > out)
			{
				break;
			}
		}

		float outwidth = 0;
		if (index <= 1)
		{
			outwidth = widthlist[0];
		}
		else if (index >= 20)
		{
			outwidth = widthlist[20];
		}
		else
		{
			
			if ((out - widthlist[index - 1]) < (widthlist[index] - out))
			{
				outwidth = widthlist[index - 1];
			}
			else
			{
				outwidth = widthlist[index];
			}
		}


		return outwidth;
	}
	
	private RenderView pose(int id, String roleId){
		User user = _userMapper.selectByPrimaryKey(id);
		Body body = _bodyMapper.selectByPrimaryKey(id);
		Pose pose = _poseMapper.findByRole(roleId);
		
		String[] motions = pose.getMotion().split(";");
		String gender = translateGender(body.getGender());
		float height = Float.parseFloat(body.getHeight().trim());
		float waist = Float.parseFloat(body.getWaist().trim());
		String sHeight ;
		waist = getfitwidth(height, waist, Integer.parseInt(gender));
		if(gender.equals(FEMALE)){
			sHeight ="166.0";
			
		}else {
			sHeight ="176.0";
		}
		String sWaist = String.valueOf((((int)waist)*10/10.0));
		Color color;
		if(TextUitls.StringNotNull(body.getEyeMidColor())){
			color =Color.decode("#"+body.getEyeMidColor());
		}else {
			color = Color.decode("#2e2e2e");
		}
		 
		String[] args;
		String[] base = new String[9];
		base[0] = sRenaderAngeCmd;
		base[1] = user.getAccount();
		base[2] = roleId;
		base[3] = sHeight;
		base[4] = sWaist;
		base[5] = String.valueOf(color.getRed()) ;
		base[6] = String.valueOf(color.getGreen()) ;
		base[7] = String.valueOf(color.getBlue()) ;
		base[8] = body.getHairId();
		args = new String[base.length+motions.length+1];
		System.arraycopy(base, 0, args, 0, base.length);  
		System.arraycopy(motions, 0, args, base.length, motions.length);
		args[args.length-1] =gender;
		List<String> urls = new ArrayList<>();
		RenderView renderView = new RenderView();
		try{
			if(CmdExec.doExec(args)){
				
				for(int i=0;i<motions.length;i++){
					String url = String.format(urlAngleFormat,user.getAccount(),sHeight
							,sWaist,roleId,body.getHairId(),motions[i]);
				
					urls.add(url);
				}
			
				renderView.setUrl(urls);
			}
		}catch(Exception e){
			renderView.setStatusCode("400");
		}
  
		renderView.setUrl(urls);
		
		return renderView;
	}
	
	private RenderView fullAngle(int id, String roleId,String motionId){
		User user = _userMapper.selectByPrimaryKey(id);
		Body body = _bodyMapper.selectByPrimaryKey(id);
		Pose pose = _poseMapper.findByRole(roleId);
	
		String gender = translateGender(body.getGender());
		float height = Float.parseFloat(body.getHeight().trim());
		float waist = Float.parseFloat(body.getWaist().trim());
		String sHeight ;
		waist = getfitwidth(height, waist, Integer.parseInt(gender));
		if(gender.equals(FEMALE)){
			sHeight ="166.0";
			
		}else {
			sHeight ="176.0";
		}
		String sWaist = String.valueOf((((int)waist)*10/10.0));
		Color color;
		if(TextUitls.StringNotNull(body.getEyeMidColor())){
			color =Color.decode("#"+body.getEyeMidColor());
		}else {
			color = Color.decode("#2e2e2e");
		}
		String[] args;
		StringBuilder buffer = new StringBuilder();
		
		args = new String[11];
		args[0] = sRenderAllCmd;
		args[1] = user.getAccount();
		args[2] = roleId;
		args[3] = sHeight;
		args[4] = sWaist;
		args[5] = motionId;
		args[6] = String.valueOf(color.getRed()) ;
		args[7] = String.valueOf(color.getGreen()) ;
		args[8] = String.valueOf(color.getBlue()) ;
		args[9] = body.getHairId();
		args[10] = gender;
		List<String> urls = new ArrayList<>();
		RenderView renderView = new RenderView();
		try{
			if(CmdExec.doExec(args)){
				for(int i=1;i<9;i++){
					String url = String.format(urlAllFormat, user.getAccount(),sHeight
							,sWaist,roleId,body.getHairId(),motionId,String.valueOf(i));
					urls.add(url);
				}
			
				renderView.setUrl(urls);
			}
		}catch(Exception e){
			
			renderView.setStatusCode("400");
		}
		

		return renderView;
		
		
	}





}
