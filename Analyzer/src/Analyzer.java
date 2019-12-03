import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Analyzer {

	public File currentFile;
	public String answer = "";
	String line = "";
	
	public BufferedReader GetOutputFile(String fileName) throws IOException {
		String filepath = "./OutputFiles/" + fileName;
		File file = new File(filepath);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		return br;
		
	}
	
	public ArrayList<String> GetFileContent(String filePath) throws IOException {
		ArrayList<String> Content = new ArrayList<String>();
		File file = new File(filePath);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		while((line = br.readLine()) != null){
		    Content.add(line);
		}
		br.close();
		return Content;
	}
	
	//This function will allow the user to understand if two entities have encountered each other during the simulations
	public boolean Meeting(String fileName, String person1, String person2, float minTime, float maxTime) throws IOException{
		String[] lineElems;
		List<String> person1Info = new ArrayList<String>(); //List of info for Person 1
		List<String> person2Info = new ArrayList<String>(); //list of info for Person 2
		answer = "";
		if(minTime <= maxTime && !person1.equals(person2)) { //If inserted data are correct it goes one, otherwise it will return to check inserted data
			BufferedReader br = GetOutputFile(fileName);
			br.readLine();
			while((line = br.readLine()) != null){ //unitl it reaches the end of the file
			    lineElems =  line.split(" ");
			    //Check if the line we are considering its good for our parameters (people and time interval)
			    if(lineElems[1].toString().equals(person1) && Float.parseFloat(lineElems[lineElems.length - 1].replace(",",".")) >= minTime &&
			    		Float.parseFloat(lineElems[lineElems.length - 1].replace(",",".")) <= maxTime) { // If info in the current line are in the range of our parameters it will modify the list of info
			    	person1Info.clear();
			    	person1Info = CreateMeetingInfo(lineElems);
			    }
			    else if(lineElems[1].toString().equals(person2) && Float.parseFloat(lineElems[lineElems.length - 1].replace(",",".")) >= minTime && // as for person1
			    		Float.parseFloat(lineElems[lineElems.length - 1].replace(",",".")) <= maxTime) {
			    	person2Info.clear();
			    	person2Info = CreateMeetingInfo(lineElems);
			    }
			    if(!person1Info.isEmpty() && !person2Info.isEmpty() && person1Info.get(4).equals(person2Info.get(4)) && 
			    		person1Info.get(1).equals(person2Info.get(1)) && inRange(person1Info.get(2), person2Info.get(2)) && inRange(person1Info.get(3), person2Info.get(3))) {
			    	//If info TimeStamp and Place of person1 and person2 match it will return that they have met the current place at the current timestamp
			    	if(!answer.contains("\n" + person1Info.get(0) + " and " + person2Info.get(0) + " have met in " + person1Info.get(1) + " at " + person1Info.get(4))){
			    		//it does not insert duplicated string in the answer
			    		answer = answer.concat("\n" + person1Info.get(0) + " and " + person2Info.get(0) + " have met in " + person1Info.get(1) + " at " + person1Info.get(4));
			    	}
			    }
			}
			br.close();
			if(person1Info.isEmpty() || person2Info.isEmpty()){ //If one of the two agents does not match inserted parameter the program will return to check inserted data
				answer = answer.concat("One of the two entities that you have chosen does not exist in the simulation or in that time interval"
						+ " is not represented in the simulation. \nPlease check that Scene and Population file are correctly related and if not switch them");
				return false;
			}
			if(answer.isEmpty()) //if no answer is found it will return that these entities have never met
				answer = answer.concat(person1Info.get(0) + " and " + person2Info.get(0) + " have never met");
			return true;
		}
		else{
			answer = answer.concat(minTime + " is greater than " + maxTime + " or you have chosen twice the same person");
			return false;		
		}
	}
	
	public String AllMeetings(String fileName, String person, ArrayList<String> population) throws NumberFormatException, IOException {
		String[] lineElems;
		String partialAnswer = "";
		BufferedReader br = GetOutputFile(fileName);
		answer = "";
		line = br.readLine();
		lineElems = line.split(" ");
		for(int i = 0; i < population.size(); i++) {
			if(Meeting(fileName, person, population.get(i) ,0f, Float.parseFloat(lineElems[3].replace(",", ".")))){			
				if(!answer.contains("have never met"))
					partialAnswer = partialAnswer.concat(answer);
			};
		}
		if(partialAnswer.isEmpty())
			answer = person + " have met noone";
		else
			answer = partialAnswer + "\n";
		return answer;
		
	}
	
	public List<String> CreateMeetingInfo (String[] info){//This function will create the ArrayList with useful information for the Meeting function
		List<String> aux = new ArrayList<String>();
		aux.add(info[1]);
		aux.add(info[7]);
		info[3] = info[3].replaceAll(",", "");
		info[5] = info[5].replaceAll(",", "");
		aux.add(info[3].replace("(",""));
		aux.add(info[5].replace(")",""));
		aux.add(info[info.length -1]);
		return aux;
	}
	
	public boolean GeneralCall(String fileName, String elem, String type) throws IOException {
		String[] lineElems;
		BufferedReader br = GetOutputFile(fileName);
		answer = "";
		line = br.readLine();
		lineElems = line.split(" ");
		br.close();
		if(type.equals("movements"))
			return movementsInInterval(fileName, elem, 0f, Float.parseFloat(lineElems[3].replace(",", ".")));
		else
			return PresenceInRoomInterval(fileName, elem, 0f, Float.parseFloat(lineElems[3].replace(",", ".")));
			
	}
	
	public boolean movementsInInterval(String fileName, String person, float minTime, float maxTime) throws IOException {
		String[] lineElems;
		List<String> personInfo = new ArrayList<String>();//array that will contain info about selected entity
		BufferedReader br = GetOutputFile(fileName);// take the file on which DigForReason has to work
		answer = "";
		br.readLine();
		if(minTime <= maxTime){
			while((line = br.readLine()) != null) {
				lineElems =  line.split(" ");
			    //Check if the line we are considering is good for our parameters (person and time interval)
			    if(lineElems[1].toString().equals(person) && Float.parseFloat(lineElems[lineElems.length - 1].replace(",",".")) >= minTime && 
			    		Float.parseFloat(lineElems[lineElems.length - 1].replace(",",".")) <= maxTime) {//Checkinf if info are in line with data inserted
			    	personInfo.clear();
			    	personInfo = CreateMeetingInfo(lineElems);// we use the same function used for meeting
			    	answer = answer.concat("\n" + person + " has been in " + personInfo.get(1) + " at " + personInfo.get(4));
			    }
			}
			if(personInfo.isEmpty()) {// if the person is not found it will return a warning
				answer = answer.concat(person + " is not present in the simulation or the time interval is not represented in the simulation. \nPlease check that Scene and Population files are correctly related and if not switch them");
				return false;
			}
			return true;
		}
		else {//if inserted data are wrong it will return the error
			answer = answer.concat(minTime + " is greater than " + maxTime);
			return false;
		}
	}
	
	public boolean PresenceInRoomInterval(String fileName, String room, float minTime, float maxTime) throws IOException {
		String[] lineElems;
		BufferedReader br = GetOutputFile(fileName);//take the file on which DigForReason has to work
		answer = "";//empty the answer string
		br.readLine();
		if(minTime <= maxTime) {
			while((line = br.readLine()) != null) {
				lineElems =  line.split(" ");
				if(lineElems[7].toString().equals(room) && Float.parseFloat(lineElems[lineElems.length - 1].replace(",", ".")) >= minTime && 
						Float.parseFloat(lineElems[lineElems.length - 1].replace(",", ".")) <= maxTime) {// check that info are in line with inserted data
					if(!answer.contains(lineElems[1])) {
						answer = answer.concat(lineElems[1] + "\n");
					}
				}
			}
			if(answer.isEmpty())
				answer = answer.concat("noone has been in " + room);
			return true;
		}
		else {
			answer = answer.concat(minTime + " is greater than " + maxTime);
			return false;
		}
	}
	
	public void CreateFile(String outputFile, String popFile, String roomFile) throws FileNotFoundException, UnsupportedEncodingException
	{
		String name = outputFile.replace(".txt", "") + popFile.replace(".txt", "") + roomFile.replace(".txt", "") + ".txt";
		File file = new File("./CommunicationFiles/" + name);
		if(!file.exists()) {
			try {
				file.createNewFile();
				System.out.println("File "+ name + " has been created");
			} catch (IOException e) {
				System.out.println("There have been problem with the creation of your file");
				e.printStackTrace();
			}
		}
		currentFile = file; 
	}
	
	public boolean inRange(String numberToCheck, String intervalNumber) {
		float check = Float.parseFloat(numberToCheck);
		float interval = Float.parseFloat(intervalNumber);
		if(check >= interval - 10 && check <= interval + 10)
			return true;
		return false;
	}

}

	
