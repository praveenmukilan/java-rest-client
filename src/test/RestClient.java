package test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.time.Instant;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import com.google.gson.Gson;


import java.util.ArrayList;
public class RestClient {
	
	private static List<Trader> allTradersList;
	private static List<Transactions> allTransactionsList;
	
	RestClient(){
	
	}

		public static void main(String[] args) {
			String transJson = getTransactionsJson();
			String tradersJson = getTradersJson();
//			System.out.println(transJson);
			Transactions[] transA = new Gson().fromJson(transJson, Transactions[].class);
			Trader[] traderA = new Gson().fromJson(tradersJson, Trader[].class);
			allTransactionsList = Arrays.asList(transA);
			allTradersList = Arrays.asList(traderA);
	
			System.out.println("Print SG Traders by Ascending Order");

			allTradersList
					.stream()
					.filter(d->d.getCity().equals("Singapore"))
					.map(d->d.getName())
					.sorted()
					.collect(Collectors.toList())				
					.forEach(name->System.out.println(name));


			//Max value transaction	
			System.out.println("Max value of all transactions : "+
												allTransactionsList
														.stream()
														.map(d->d.getValue())
														.max(Double::compare).get()
							  );

			
	
			//transactions in 2016 ordered by value DESC
			System.out.println("All transactions in 2016 ordered by value Desc");
			
			allTransactionsList
					.stream()
					.filter(d->getYear(new Date(d.getTimeStamp()*1000))==2016)
					.map(d->d.getValue())
					.sorted((d1,d2)->Double.compare(d2, d1))
					.collect(Collectors.toList())
					.forEach(value->System.out.println(value));
			

			
			//avg of transaction value for traders living in beijing
			
			System.out.println("average of transctions by Beijing traders is : "+
			allTransactionsList
					.stream()
					.filter(
							  d-> (allTradersList
										.stream()
										.filter(a->a.getCity().equals("Beijing"))
										.map(f->f.getId())
										.collect(Collectors.toList())
										.contains(d.getTraderId()))
						   )
					.mapToDouble(e->e.getValue())
					.average()
					.getAsDouble());
				

			
		}
		
		
		public static int getYear(Date date){
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			return cal.get(Calendar.YEAR);
		}
		
		public static String getTransactionsJson(){				
			return getResponse("https://fvjkpkflnc.execute-api.us-east-1.amazonaws.com/prod/transactions");
		}
		
		public static String getResponse(String urlStr){
			String response="";
			try{
			
			URL url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("x-api-key", "gaqcRZE4bd58gSAJH3XsLYBo1EvwIQo88IfYL1L5");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

			String output;
			
			System.out.println("Server Response ...\n");
			while ((output = br.readLine()) != null) {				
//				System.out.println(output);
				response+=output;
			}
			conn.disconnect();

		  } catch (MalformedURLException e) {
			e.printStackTrace();
		  } catch (IOException e) {
			e.printStackTrace();

		  }

		return response;
		}
		
	
		public static String getTradersJson(){
			return getResponse("https://fvjkpkflnc.execute-api.us-east-1.amazonaws.com/prod/traders");
		}
		
		
		public static ArrayList<Trader> getTradersList(){
			String tradersString=getTradersJson();
			System.out.println(tradersString);			

			Trader[] tradersA=new Gson().fromJson(tradersString, Trader[].class);			
		
			ArrayList<Trader> listOfTraders = new ArrayList<>(Arrays.asList(tradersA));
			
			return listOfTraders;
		}

		

	}


