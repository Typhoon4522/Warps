package me.warps.core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class CustomConfig {
	
	public void defaultCreateConfig(){
		File folder1 = new File("plugins/Warps");
		File folder2 = new File("plugins/Warps/Config");
		
		if(!(folder1.exists())){
			folder1.mkdir();
		}
		if(!(folder2.exists())){
			folder2.mkdir();
		}
	}
	
	public void addPlayerInCofing(Player player, Location loc, String warpName){
		File folder1 = new File("plugins/Warps");
		File folder2 = new File("plugins/Warps/Config");
		
		File filename1 = new File("plugins/Warps/"+player.getUniqueId()+".json");
		File filename2 = new File("plugins/Warps/config/options.json");
		
		BufferedWriter w = null;
		JSONObject json = new JSONObject();
		JSONObject json2 = new JSONObject();
		
		try{
			if(!(folder1.exists())){
				folder1.mkdir();
			}
			if(!(folder2.exists())){
				folder2.mkdir();
			}
			
			if(!(filename1.exists())){
				filename1.createNewFile();
				w = new BufferedWriter(new FileWriter(filename1));
				
				json.put(warpName, loc);
				
				w.append(json.toJSONString());
				w.flush();
				w.close();
			}
			
			if(!(filename2.exists())){
				filename2.createNewFile();
				w = new BufferedWriter(new FileWriter(filename2));
				
				json2.put("whitelist", "false");
				json2.put("limit", 5);
				
				w.append(json2.toJSONString());
				w.flush();
				w.close();
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	/*
	
	public String cutter(String line){
		String[] str = line.split(":");
		return str[1];
	}
	
	public String getConfigData(Player player){
		File folder1 = new File("plugins/System");
		File filename = new File("plugins/System/"+player.getUniqueId()+".txt");
		String result = "";
		
		try{
			BufferedReader r = new BufferedReader(new FileReader(filename));
			result = cutter(r.readLine());
		}
		catch(IOException io){
			io.printStackTrace();
		}
		
		return result;
	}
	*/
	
	public boolean setConfigData(Player player, String key, String value){
		
		
		return true;
	}
	
	public boolean setConfigData(Player player, String key, int value){
		
		
		return true;
	}
	
	public String getConfigOption(){
		JSONParser pare = new JSONParser();
		String result = null;
		
		try{
			Object obj = pare.parse(new FileReader("plugins/Warps/config/options.json"));
			JSONObject jobj = (JSONObject)obj;
			result = (String)jobj.get("whitelist");
		}
		catch(org.json.simple.parser.ParseException ex){
			ex.printStackTrace();
		}
		catch(FileNotFoundException ex){
			ex.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public Location getConfigData(Player player, String key)throws org.json.simple.parser.ParseException{
		JSONParser parse = new JSONParser();
		Location result = null;
		
		try{
			Object obj = parse.parse(new FileReader("plugins/Warps/"+player.getUniqueId()+".json"));
			JSONObject jobject = (JSONObject)obj;
			result = (Location)jobject.get(key);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} 
		catch (IOException e) {
			e.printStackTrace();
			return null;
		} 
		
		return result;
	}
	
	//해당 플레이어의 콘피그가 있나 없나 확인
	public boolean isLive(Player player){
		File filename = new File("plugins/Warps/"+player.getUniqueId()+".json");
		return filename.exists();
	}
}
