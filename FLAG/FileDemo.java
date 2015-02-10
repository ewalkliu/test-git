import java.util.*;
import java.io.*;

public class FileDemo  
{  
    public static void main (String[] args) throws IOException
    {  
        File f=new File("/home/zliu/java-test/FLAG/1");  
        if(f.exists())  
        {  
			System.out.println("exists");
            //f.delete();  
        }  
        else  
        {  
            try  
            {  
                f.createNewFile();    
            }  
            catch(Exception e)  
            {  
                System.out.println(e.getMessage());  
            }  
        }  
        //文件名  
        System.out.println("The file name is "+f.getName()+".");  
        //路径名  
        System.out.println("The path is "+f.getPath()+".");  
        //绝对路径名  
        System.out.println("The absolute path is "+f.getAbsolutePath()+".");  
        //父目录  
        System.out.println("The father directory is "+f.getParent()+".");  
        //文件是否存在  
        System.out.println(f.exists()?"The file exists.":"The file does not exist.");  
        //文件是否可写  
        System.out.println(f.canWrite()?"The file can been writen.":"The file can not been writen.");  
        //文件是否可读  
        System.out.println(f.canRead()?"the file can been read":"The file can not been read.");  
        //文件句柄是否是一个目录  
        System.out.println("The file "+(f.isDirectory()?"is":"is not")+" a directory.");  
        //文件句柄是否是一个绝对路径  
        System.out.println("The object "+(f.isAbsolute()?"is an absolute path":"is not an absolute path"));  
        //文件最后被修改的时间  
        System.out.println("The file has been modified at "+f.lastModified()+".");  
        //文件长度  
        System.out.println("The length of the file is "+f.length()+" Bytes.");  
		f.delete();
		f.createNewFile();
		FileWriter fw = new FileWriter(f.getParent()+"/"+f.getName());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("zhuo");
		bw.newLine();
		bw.write("liu");
		bw.close();
		//f.close();
		FileReader is = new FileReader(f.toString());
		BufferedReader br = new BufferedReader(is);
		
		String s = null;
		while((s=br.readLine())!=null){
			System.out.println(s);
		
		}
		System.out.println(f.getPath());
		is.close();
		br.close();

    }  
}
