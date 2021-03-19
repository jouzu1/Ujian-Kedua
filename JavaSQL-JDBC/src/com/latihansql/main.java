package com.latihansql;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class main {
	
	/**
	 * Latihan menyambungkan database ke eclipse
	 * Import beberapa interfaces
	 */
	
	//Buat driver
	static final String JDBC = "com.mysql.jdbc.Driver";
	
	//Masukkan host
	static final String DB_URL = "jdbc:mysql://localhost/ujian kedua";
	
	//user
	static final String DB_User = "root";
	
	//password
	static final String DB_Password = "";
	
	//Objek Interface untuk nyambungin ke database
	static Connection conn;
	
	//Objek untuk membuat statment static
	static Statement stat;
	
	//Objek untuk menampilkan hasil
	static ResultSet rs;
	
	static InputStreamReader data = new InputStreamReader(System.in);
	static BufferedReader br = new BufferedReader(data);
	
//	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try 
		{
			Class.forName(JDBC);
			conn = DriverManager.getConnection(DB_URL, DB_User, DB_Password);
			stat = conn.createStatement();
			
			while(!conn.isClosed()) {
			
				showMenu();
				
			}
			stat.close();
			conn.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
}
	static void showMenu() {
		System.out.println("Selamat Datang Di Pinjaman Dana KPR");
		System.out.println("===========================");
		System.out.println("1. Butuh Pinjaman Dana");
		System.out.println("2. Lihat Simulasi Pembayaran Cicilan");

		System.out.println("3. Keluar");
		System.out.print("Masukkan pilihan berdasarkan menu di atas : ");
		
		try {
			int opt = Integer.parseInt(br.readLine());
			switch(opt) {
			case 1:
				reqDana();
				break;
			case 2:
				showData();
				break;
			case 3:
				System.out.println("Anda telah keluar dari menu ini dan/atau Data tidak ada");
				deleteData();				
				System.exit(0);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	static void showData() {
		String qry = "SELECT * FROM datapinjaman";
		try {
			rs = stat.executeQuery(qry);
			System.out.println("Simulasi Cicilan");
			System.out.println("===========================");
			while(rs.next()){
				int angsuranKe = rs.getInt("angsuranke");
//				String nama = rs.getString("tanggal");
				int totalAngsuran = rs.getInt("totalAngsuran");
				int angsuranPokok = rs.getInt("angsuranPokok");
				int angsuranBunga = rs.getInt("angsuranBunga");
				int sisaPinjaman = rs.getInt("sisaPinjaman");
//				int umur = rs.getInt("umur");
				
				System.out.println(String.format("Angsuran Ke : %d | Total Angsuran : %d | Angsuran Pokok : %d | Angsuran Bunga : %d | Sisa Pinjaman : %d", angsuranKe,totalAngsuran,angsuranPokok,angsuranBunga,sisaPinjaman));
				System.out.println();
				System.out.println();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		}
		
		static void reqDana() {
			System.out.println("Masukkan Data Dibawah Ini");
			System.out.println("===========================");
			
			try {
				int angsuranke = 0;
				System.out.print("Masukkan jumlah dana yang di inginkan : ");
				int platfon = Integer.parseInt(br.readLine());
				
				System.out.print("Jumlah suku bunga tahunan yang di inginkan : ");
				double bunga = Double.parseDouble(br.readLine());
				
				System.out.print("Ingin di cicil berapa bulan ? : ");
				int lamapinjam = Integer.parseInt(br.readLine());
				
//				System.out.print("Masukkan Umur : ");
//				int umur = Integer.parseInt(br.readLine());
				/**
		         * Total Angsuran
		         * Angsuran Pokok
		         * Angsuran Bunga
		         * Sisa Pinjaman
		         */
		        
		        double sukubunga = 1.2/12;
		        int lamapinjam1 = 15;
		        int uang = 20000000;
		        double bunga1 = 1.2;
		        
		        double atas = Math.pow(1 + bunga/12, lamapinjam);
		        double bawah = Math.pow(1 + bunga/12, lamapinjam)-1;
		        double gabung = atas/bawah;
		        double modif = 1-(Math.pow(1+(bunga/12), -lamapinjam));
		        
		        
		        
		        double totalAngsuran = platfon * ((bunga/12) / modif);
		        double angsuranBunga = (bunga/12)*platfon*(Math.pow(((1+(bunga/12))),lamapinjam))/(Math.pow(((1+(bunga/12))),lamapinjam))-1;                
		        double angsuranPokok = totalAngsuran - angsuranBunga;
		        double sisapinjaman = platfon - angsuranPokok;
		        double angsuranBunga1 = platfon*(bunga/12);
		        double angsuranPokok1 = totalAngsuran - angsuranBunga1;

//				String qry = "INSERT INTO `mahasiswa`(`nim`, `nama`, `alamat`, `umur`) VALUES (%d,'%s','%s',%d)";
		       
		        
		        for(int i = 0; i<lamapinjam;i++) {
		        	String qry = "INSERT INTO `datapinjaman`(`totalAngsuran`, `angsuranPokok`, `angsuranBunga`, `sisaPinjaman`) "
							+ "VALUES ( %f , %f, %f, %f)";
		        	qry = String.format(qry,totalAngsuran,angsuranPokok1+=100000,angsuranBunga1+=1000000,sisapinjaman-=1000000);
		        	stat.execute(qry);
		        }
		        	
		        
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		static void deleteData() {
			try {
				String qry = "TRUNCATE TABLE datapinjaman";
						

				
				
				
				stat.execute(qry);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
//		static void updateData() {
//			try {
//				System.out.print("Masukkan NIM Yang Ingin Di ubah: ");
//				int nim = Integer.parseInt(br.readLine());
//				System.out.print("Masukkan Nama : ");
//				String nama = br.readLine();
//				System.out.print("Masukkan Alamat : ");
//				int alamat = Integer.parseInt(br.readLine());
//				System.out.print("Masukkan Umur : ");
//				int umur = Integer.parseInt(br.readLine());
//				
//				String qry = "UPDATE `mahasiswa` SET `nim`=%d,`nama`='%s,`alamat`='%s,`umur`=%d";
//				qry = String.format(qry, nim,nama,alamat,umur);
//				stat.execute(qry);
//			}catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		static void deleteData() {
//			try {
//				System.out.print("Masukan Nim yang ingin di hapus= ");
//				int nim = Integer.parseInt(br.readLine());
//
//				String qry = "DELETE FROM `mahasiswa` WHERE  nim = %d";
//
//				qry = String.format(qry, nim);
//				
//				stat.execute(qry);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			
//		}
	}

