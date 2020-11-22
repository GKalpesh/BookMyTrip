package com.example.bookmytrip;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class database extends SQLiteOpenHelper {

    private static final String dbname ="mydb";
    private static final int version = 1;

    public database(Context context){
        super(context,dbname,null,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE TRAINTIMING(_id INTEGER PRIMARY KEY AUTOINCREMENT, STATIONS STRING,PLATFORM STRING,TIMING STRING)";
        db.execSQL(sql);

        //insert data from nerul station to others
        //NERUL TO ANDHERI
        insertdata("NERUL-ANDHERI","PF-4","5.00-6.13",db);
        insertdata("NERUL-ANDHERI","PF-4","6.00-7.23",db);
        insertdata("NERUL-ANDHERI","PF-4","7.00-8.13",db);
        insertdata("NERUL-ANDHERI","PF-4","8.00-9.13",db);
        insertdata("NERUL-ANDHERI","PF-4","10.00-11.13",db);
        insertdata("NERUL-ANDHERI","PF-4","12.10-13.23",db);
        insertdata("NERUL-ANDHERI","PF-4","14.00-15.13",db);
        insertdata("NERUL-AIROLI","PF-3","6.00-5.22",db);
        insertdata("NERUL-AMBERNATH","PF-3","7.00-5.22",db);
        insertdata("NERUL-ASANGAON","PF-3","5.00-5.22",db);
        insertdata("NERUL-BADLAPUR","PF-3","5.00-5.22",db);
        insertdata("NERUL-BANDRA","PF-3","5.00-5.22",db);
        insertdata("NERUL-BELAPUR","PF-3","5.00-5.22",db);
        insertdata("NERUL-BHANDUP","PF-3","5.00-5.22",db);
        insertdata("NERUL-BHIVPURI","PF-3","5.00-5.22",db);
        insertdata("NERUL-BHIWANDI","PF-3","5.00-5.22",db);
        insertdata("NERUL-BOISAR","PF-3","5.00-5.22",db);
        insertdata("NERUL-BORIVALI","PF-3","5.00-5.22",db);
        insertdata("NERUL-BYCULLA","PF-3","5.00-5.22",db);
        insertdata("NERUL-CSMT","PF-3","5.00-5.22",db);
        insertdata("NERUL-CHARNI ROAD","PF-3","5.00-5.22",db);
        insertdata("NERUL-CHEMBUR","PF-3","5.00-5.22",db);
        insertdata("NERUL-CHINCHPOKLI","PF-3","5.00-5.22",db);
        insertdata("NERUL-CHUNABHATTI","PF-3","5.00-5.22",db);
        insertdata("NERUL-CHURCHGATE","PF-3","5.00-5.22",db);
        insertdata("NERUL-COTTON GREEN","PF-3","5.00-5.22",db);
        insertdata("NERUL-CURREY ROAD","PF-3","5.00-5.22",db);
        insertdata("NERUL-DADAR","PF-3","5.00-5.22",db);
        insertdata("NERUL-DOMBIVLI","PF-3","5.00-5.22",db);
        insertdata("NERUL-GTB NAGAR","PF-3","5.00-5.22",db);
        insertdata("NERUL-GHANSOLI","PF-3","5.00-5.22",db);
        insertdata("NERUL-GHATKOPAR","PF-3","5.00-5.22",db);
        insertdata("NERUL-GOREGOAN","PF-3","5.00-5.22",db);
        insertdata("NERUL-GOVANDI","PF-3","5.00-5.22",db);
        insertdata("NERUL-JOGESHWARI","PF-3","5.00-5.22",db);
        insertdata("NERUL-JUINAGAR","PF-3","5.00-5.22",db);
        insertdata("NERUL-KALYAN","PF-3","5.00-5.22",db);
        insertdata("NERUL-KANDIVALI","PF-3","5.00-5.22",db);
        insertdata("NERUL-KANJUR MARG","PF-3","5.00-5.22",db);
        insertdata("NERUL-KARJAT","PF-3","5.00-5.22",db);
        insertdata("NERUL-KASARA","PF-3","5.00-5.22",db);
        insertdata("NERUL-KHANDESHAWAR","PF-3","5.00-5.22",db);
        insertdata("NERUL-KHARGHAR","PF-3","5.00-5.22",db);
        insertdata("NERUL-KOPARKHAIRNE","PF-3","5.00-5.22",db);
        insertdata("NERUL-KURLA","PF-3","5.00-5.22",db);
        insertdata("NERUL-LOWER PAREL","PF-3","5.00-5.22",db);
        insertdata("NERUL-MAHALAKSHMI","PF-3","5.00-5.22",db);
        insertdata("NERUL-MALAD","PF-3","5.00-5.22",db);
        insertdata("NERUL-MANKHURD","PF-3","5.00-5.22",db);
        insertdata("NERUL-MARINE LINES","PF-3","5.00-5.22",db);
        insertdata("NERUL-MATUNGA","PF-3","5.00-5.22",db);
        insertdata("NERUL-MASJID","PF-3","5.00-5.22",db);
        insertdata("NERUL-MUMBAI CENTRAL","PF-3","5.00-5.22",db);
        insertdata("NERUL-PALGHAR","PF-3","5.00-5.22",db);
        insertdata("NERUL-PANVEL","PF-3","5.00-5.22",db);
        insertdata("NERUL-PANVEL","PF-1","6.00-6.22",db);
        insertdata("NERUL-PANVEL","PF-2","7.00-7.22",db);
        insertdata("NERUL-PANVEL","PF-2","8.00-8.22",db);
        insertdata("NERUL-RAM MANDIR","PF-3","5.00-5.22",db);
        insertdata("NERUL-SANPADA","PF-3","5.00-5.22",db);
        insertdata("NERUL-SANTA CRUZ","PF-3","5.00-5.22",db);
        insertdata("NERUL-SEAWOOD","PF-3","5.00-5.22",db);
        insertdata("NERUL-SION","PF-3","5.00-5.22",db);

        insertdata("NERUL-THANE","PF-2","4.56-5.25",db);
        insertdata("NERUL-THANE","PF-2","5.16-5.45",db);
        insertdata("NERUL-THANE","PF-2","5.44-6.13",db);
        insertdata("NERUL-THANE","PF-2","6.07-6.36",db);
        insertdata("NERUL-THANE","PF-2","6.20-6.50",db);
        insertdata("NERUL-THANE","PF-2","6.40-7.09",db);
        insertdata("NERUL-THANE","PF-2","7.00-7.29",db);
        insertdata("NERUL-THANE","PF-2","7.08-7.38",db);
        insertdata("NERUL-THANE","PF-2","7.24-7.53",db);
        insertdata("NERUL-THANE","PF-2","7.29-8.00",db);
        insertdata("NERUL-THANE","PF-2","7.42-8.12",db);

        insertdata("NERUL-TILAKNAGAR","PF-3","5.00-5.22",db);
        insertdata("NERUL-TURBHE","PF-3","5.00-5.22",db);
        insertdata("NERUL-VADALA ROAD","PF-3","5.00-5.22",db);
        insertdata("NERUL-VASHI","PF-3","5.00-5.22",db);
        insertdata("NERUL-VIKHROLI","PF-3","5.00-5.22",db);
        insertdata("CSMT-VIRAR","PF-3","5.00-5.22",db);

        //express
        insertdata("CSMT-SURAT","PF-10","5.00-10.13",db);
        insertdata("CSMT-PUNE JN","PF-3","17.00-21.30",db);
        insertdata("CSMT-NEW DELHI","PF-3","6.00-20.00",db);
        insertdata("PATNA-AHMEDABAD","PF-3","11.00-5.22",db);

        insertdata("CSMT-KOLHAPUR","PF-7","08.40-20.25",db);
        insertdata("CSMT-KOLHAPUR","PF-7","17.50-06.05",db);
        insertdata("CSMT-KOLHAPUR","PF-8","20.20-07.25",db);
        insertdata("CSMT-KOLHAPUR","PF-7","03.40-15.25",db);

        insertdata("SURAT-KOLHAPUR","PF-3","03.40-15.25",db);


    }

    private void insertdata(String stations,String platform,String timing,SQLiteDatabase database){
        ContentValues values = new ContentValues();
        values.put("STATIONS",stations);
        values.put("PLATFORM",platform);
        values.put("TIMING",timing);
        database.insert("TRAINTIMING",null,values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
