package Main_App;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import Data_Setup.Record;


/**
 * This class building ArrayList with the All data and sorting them.
 * Additionally, Write the sorted data to CSV File.
 */

public class Write_2_CSV {

	/**
	 * All_Data_List this the ArrayList with all data.
	 */
	public static final ArrayList<Record> All_Data_List = new ArrayList<Record>();

	public static void clearData() {
		All_Data_List.clear();
	}

	/**
	 *  Write_Headers Create CSV file with Headers.
	 */
	public static void Write_Headers(String savefile) throws IOException{
		String[] titles_List = {"Time","ID","Lat","Lon","Alt","#WiFi networks","SSID","MAC","Frequency","Signal"};
		FileWriter file = new FileWriter(savefile, true);

		for (int i = 0; i <= 5; i++) {
			file.write(titles_List[i]+",");
		}

		for (int i = 1; i <= 10; i++) {
			for (int j = 6; j < titles_List.length; j++) {
				file.write(titles_List[j]+i+",");
			}
		}

		file.close();
	}

	/**
	 * Write_2_CSV Get the ArrayList from the Analyze function and copy the data from it in a sorted way.
	 * Write to the CSV file with Headers the new sorted ArrayList.
	 */
	public static void Build_ArrayList(ArrayList<Record> data_list) throws IOException{


		ArrayList<Record> sorted_data_list = new ArrayList<Record>();


		for(int i = 0; i < data_list.size()-1; i++) {

			if(data_list.get(i).compare(data_list.get(i+1)) == true) {
				data_list.get(i+1).mergeWifiList(data_list.get(i).getWifiList());
			}
			else {		
				sorted_data_list.add(data_list.get(i));
			}			
		}


		for (int i = 0; i < sorted_data_list.size(); i++) {

			sorted_data_list.get(i).setWifinetworks(sorted_data_list.get(i).getWifiList().size());

		}

		All_Data_List.addAll(sorted_data_list);
		sorted_data_list.clear();
		data_list.clear();

	}

	/**
	 * Write the sorted data to file.
	 * @param savefile name of file to save.
	 * @throws IOException
	 */
	public static void Write(String savefile) throws IOException {
		Write_2_CSV.Write_Headers(savefile);
		StringBuilder stringBuilder = new StringBuilder();
		FileWriter fw = new FileWriter(savefile, true);
		for (int i = 0; i < All_Data_List.size(); i++) {
			stringBuilder.append("\n");
			stringBuilder.append((All_Data_List.get(i).toString().replace("[", "").replace("]", "")));	
		}

		fw.write(stringBuilder.toString());
		fw.close();

	}
}
