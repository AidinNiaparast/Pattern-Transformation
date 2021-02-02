<%@page import="java.util.Scanner"%>
<%@page import="java.io.File"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Leader Board</title>
</head>
<body>

<% 
	File f = new File("C:\\Users\\Aidin\\eclipse-workspace\\project4\\leaderBoardDB.txt");
	Scanner sc = new Scanner(f);

	//for(int i=1;i<=15;i++){
	int line=1;	
	while(sc.hasNextLine()){
		if(line==Integer.parseInt(request.getParameter("level"))){	
			String s=sc.nextLine();
			String[] records=s.split(" ");
			int[] times = new int[records.length];
	        int[] moves = new int[records.length];
	        String[] names = new String[records.length];
			int length=0;
			
	        for (int i = 0; i < records.length;i++) {
	        	//System.out.println(records[i].split(",")[0]);
	        	//System.out.println(records[i]);
	        	//System.out.println(records[i].split(",").length);
	        	if(records[i].split(",").length==3){
		            moves[length] = Integer.parseInt(records[i].split(",")[1]);
			        times[length] = Integer.parseInt(records[i].split(",")[2]);
			        names[length] = records[i].split(",")[0];
			        length++;
	        	}
	        }

	        
	        /*for(int i=0;i<length;i++)
	        	System.out.println(times[i]);*/
	        	
	        	
        	for (int i = 0; i < length; i++) {
                for (int j = 0; j < length - i - 1; j++) {
                    if (times[j] > times[j + 1]) {
                        int temptime = times[j];
                        times[j] = times[j + 1];
                        times[j + 1] = temptime;

                        String tempname = names[j];
                        names[j] = names[j + 1];
                        names[j + 1] = tempname;
                    }
                }
            }
        	
        	/*System.out.println();
        	for(int i=0;i<length;i++)
	        	System.out.println(times[i]);
			*/
			
            out.println("<h1>");
            out.println("leaderboard: based on time");
            out.println("<br/>");
            out.println("</h1>");
            out.println("<h2>");
            for (int i = 0; (i < length && i<3); i++) {
                out.println(i + 1 + ". " + names[i] + "   " + times[i]);
                out.println("<br/>");
            }

            for (int i = 0 , j = 0 ; i < records.length; i++) {
                if(records[i].split(",").length==3){
            		names[j] = records[i].split(",")[0];
            		j++;
                }
                //System.out.println("records[i]= "+records[i]);
            }

            for (int i = 0; i < length; i++) {
                for (int j = 0; j < length - i - 1; j++) {
                    if (moves[j] > moves[j + 1]) {
                        int tempmove = moves[j];
                        moves[j] = moves[j + 1];
                        moves[j + 1] = tempmove;

                        String tempname = names[j];
                        names[j] = names[j + 1];
                        names[j + 1] = tempname;
                    }
                }
            }

            
            out.println("<br/>");
            out.println("</h2>");
            out.println("<h1>");
            out.println("leaderboard: based on number of moves");
            out.println("<br/>");
            out.println("</h1>");

            out.println("<h2>");
            for (int i = 0;( i < length && i < 3); i++) {
                out.println(i + 1 + ". " + names[i] + "   " + moves[i]);
                out.println("<br/>");
            }
            out.println("</h2>");
		
			/*TreeMap <Integer,String> moveRecords=new TreeMap<Integer,String>();
			String[] records=s.split(" ");
			for(String record:records){
				if(record.split(",").length==3)
					moveRecords.put(Integer.parseInt(record.split(",")[1]),record.split(",")[0]);
			}
			
			out.println("leaderboard: based on number of moves");
			out.println("<br/>");
			
			
			int j=0;
			for(Map.Entry<Integer,String> entry : moveRecords.entrySet()) {
				  if(!entry.getValue().equals("admin")){
				  		out.println(j+1+". "+entry.getValue()+"   "+entry.getKey());
				  		out.println("<br/>");
				  		j++;
				  }
				  if(j==3)
					  break;
				}
			out.println("<br/>");
			out.println("<br/>");
			
			
			TreeMap<Long,String> timeRecords=new TreeMap<Long,String>();
			for(String record:records){
				if(record.split(",").length==3)
					timeRecords.put(Long.parseLong(record.split(",")[2]),record.split(",")[0]);
			}
			
			out.println("leaderboard: based on time");
			out.println("<br/>");
			
			j=0;
			for(Map.Entry<Long,String> entry : timeRecords.entrySet()) {
				if(!entry.getValue().equals("admin")){
				  out.println(j+1+". "+entry.getValue()+"   "+entry.getKey());
				  out.println("<br/>");
				  j++;
				}
				if(j==3)
					break;
			}
			*/				
		}
		else
			sc.nextLine();
		line++;
	}
	sc.close();
%>

<br/>
<br/>
<a href="levels.jsp"> levels </a>

</body>
</html>