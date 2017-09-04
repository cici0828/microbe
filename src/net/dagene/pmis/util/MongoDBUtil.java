package net.dagene.pmis.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.dagene.pmis.pathology.po.CsltSliceInfoCustom;
import net.dagene.pmis.pathology.vo.CsltSliceVo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.bson.BSON;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MongoDBUtil {
	static String dbName = "lims";
	static MongoClient client;
	static MongoDatabase db;

	static {
		try {
			ServerAddress serverAddress = new ServerAddress("192.168.0.217",
					27017);
			List<ServerAddress> seeds = new ArrayList<ServerAddress>();
			seeds.add(serverAddress);
			MongoCredential credentials = MongoCredential
					.createMongoCRCredential("testAdmin", "test",
							"123".toCharArray());
			List<MongoCredential> credentialsList = new ArrayList<MongoCredential>();
			credentialsList.add(credentials);
			client = new MongoClient(seeds);
			client.listDatabaseNames().first();
			System.out.println("mongodb初始化成功！");
		} catch (Exception e) {
			System.out.println("mongodb初始化失败！");
			e.printStackTrace();
		}
	}

	public static Document getDocumentByJSON(String json) {
		try {
			return Document.parse(json);
		} catch (Exception e) {
			return null;
		}
	}

	/* cslt */
	public static void addOne(String collectionName, Document value)
			throws Exception {
		client.getDatabase(dbName).getCollection(collectionName)
				.insertOne(value);
	}

	public static void delete(String collectionName, Document criterion)
			throws Exception {
		client.getDatabase(dbName).getCollection(collectionName)
				.deleteMany(criterion);
	}

	public static void update(String collectionName, Document criterion,
			Document value) throws Exception {
		client.getDatabase(dbName).getCollection(collectionName)
				.updateMany(criterion, value);
	}
	
	public static void update(String collectionName, Document criterion,
			Document value, String db) throws Exception {
		client.getDatabase(db).getCollection(collectionName)
				.updateMany(criterion, value);
	}
	
	public static ArrayList<String> findJSON(String collectionName, Bson filter, String db){
		ArrayList<String> JSONlist = new ArrayList<String>();
		FindIterable<Document> ret = client.getDatabase(db)
				.getCollection(collectionName).find(filter);
		MongoCursor<Document> it = ret.iterator();
		for (; it.hasNext();) {
			String value = it.next().toJson();
			//System.out.println(value);
			JSONlist.add(value);
		}
		return JSONlist;
		
	}

	public static ArrayList<String> findJSON(String collectionName, Bson filter)
			throws Exception {
		return findJSON(collectionName, filter, dbName);
	}

	public static ArrayList<String> findJSON(String collectionName,
			String filter) throws Exception {
		return findJSON(collectionName, getDocumentByJSON(filter));
	}
	
	public static ArrayList<String> findJSON(String db, String collectionName,
			String filter) throws Exception {
		return findJSON(collectionName, getDocumentByJSON(filter), db);
	}

	public static void main(String[] args) {
		try {
			MongoDBUtil.update("sliceinfo",MongoDBUtil.getDocumentByJSON("{barcode:"+ "'0001'" +"}"), 
					MongoDBUtil.getDocumentByJSON("{$set: {update: 0}}"), "slice");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		try {
//			//MongoDBUtil.findJSON("cslt", "{state: {$in: ['16','17']}}");
//			ArrayList<String> list=MongoDBUtil.findJSON("slice", "sliceinfo", "{}");
//			ArrayList<CsltSliceVo> sliceList  = new ArrayList<CsltSliceVo>();
//			Iterator<String> it=list.iterator();
//			while(it.hasNext()){
//				String str = it.next();
//				JSONObject js = JSONObject.fromObject(str);
//				CsltSliceVo csltSliceVo = new CsltSliceVo();
//				CsltSliceInfoCustom csltSliceInfoCustom = new CsltSliceInfoCustom();
//				
//				csltSliceVo.setBarcode(js.getString("barcode"));
//				//csltSliceVo.setCsltSliceInfoCustom(csltSliceInfoCustom);
//				
//				
//				JSONArray slices = js.getJSONArray("slicelist");
//				for(int i = 0; i < slices.size(); i ++ ){
//					JSONObject slice = slices.getJSONObject(i);
//					csltSliceInfoCustom.setDiancode(csltSliceVo.getBarcode());
//					csltSliceInfoCustom.setDsno(slice.getString("sliceid"));
//					csltSliceInfoCustom.setShortpicurl(slice.getString("slice_shot"));
//					csltSliceInfoCustom.setSlicedesc(slice.getString(" slicedesc"));					
//				}				
//				sliceList.add(csltSliceVo);				
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

}