package ttt_ai;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class NNFiler {
	public void write(NN nn, int id) {
		try {
			FileOutputStream fo = new FileOutputStream(new File(String.valueOf(id) + ".txt"));
			ObjectOutputStream oo = new ObjectOutputStream(fo);

			// Write objects to file
			oo.writeObject(nn);

			oo.close();
			fo.close();

			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public NN read(int id) {
		FileInputStream fi;
		try {
			fi = new FileInputStream(new File(String.valueOf(id) + ".txt"));
			ObjectInputStream oi = new ObjectInputStream(fi);

			// Read objects
			NN retNN = (NN)oi.readObject();

			oi.close();
			fi.close();
			
			return retNN;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
}
