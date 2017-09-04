package net.dagene.net.microbe.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import net.dagene.microbe.mapper.MicrobeMapper;
import net.dagene.microbe.service.MicrobeService;
import net.dagene.microbe.vo.QueryMicrobeParamVo;
import net.dagene.microbe.vo.QueryMicrobeResultVo;
import net.dagene.pmis.system.service.UsersService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MicrobeServiceImpl implements MicrobeService {
	MicrobeServiceImpl() {

	}

	@Autowired
	private MicrobeMapper mm;

	@Autowired
	private UsersService um;

	private String getYYYYMMDDHHMMSS(Date date) {
		if (date == null)
			return "";
		else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.format(date);
		}
	}

	@Override
	public String getResult(QueryMicrobeParamVo p) throws Exception {
		JSONArray infoList = new JSONArray();
		List<QueryMicrobeResultVo> resultList = mm.queryMicrobeResult(p);

		for (int i = 0; i < resultList.size(); i++) {
			QueryMicrobeResultVo r = resultList.get(i);
			r.setInputmanName(um.GetUserFullName(r.getInputman()));
			r.setCheckmanName(um.GetUserFullName(r.getCheckman()));
			r.setApprovalmanName(um.GetUserFullName(r.getApprovalman()));

			JSONObject info = JSONObject.fromObject(resultList.get(i));

			info.put("collectddate", getYYYYMMDDHHMMSS(r.getCollectddate()));
			info.put("senddate", getYYYYMMDDHHMMSS(r.getSenddate()));
			info.put("receiveddate", getYYYYMMDDHHMMSS(r.getReceiveddate()));
			info.put("reportdate", getYYYYMMDDHHMMSS(r.getReportdate()));
			info.put("inputdate", getYYYYMMDDHHMMSS(r.getInputdate()));
			//info.put("checkdate", getYYYYMMDDHHMMSS(r.getCheckdate()));
			//info.put("approvaldate", getYYYYMMDDHHMMSS(r.getApprovaldate()));
			JSONObject result = new JSONObject();
			JSONArray dl = info.getJSONArray("detailList");
			if ((dl != null) && (dl.size() > 0)) {
				int j = 0;
				String f50 = dl.getJSONObject(j).getString("f50")
						.replace(".frf", "").replace(".fr3", "");
				if (f50.equals("杭州无菌1抬头模板") || f50.equals("物体表面培养V2")
						|| f50.equals("手部细菌培养V2")) {
					if (f50.equals("杭州无菌1抬头模板"))
						result.put("type", "10");//'118462691'
					else if (f50.equals("物体表面培养V2"))
						result.put("type", "40"); //'197836100'
					else if (f50.equals("手部细菌培养V2"))
						result.put("type", "42");//'124867621'
					JSONArray valueList = new JSONArray();
					for (j = 0; j < dl.size(); j++) {
						String f1 = dl.getJSONObject(j).getString("f1");
						valueList.add(j, f1);
					}
					result.put("value", valueList);
					info.remove("detailList");
					info.put("result", result);
				} else if (f50.equals("杭州药敏仪器")) {// '123377298','125914238','123086507'
					result.put("type", "15");
					JSONArray valueList = new JSONArray();
					String oldPfn = "";
					int index = 0;
					JSONObject value = null;
					JSONArray ymList = null;
					for (j = 0; j < dl.size(); j++) {
						JSONObject d = dl.getJSONObject(j);
						String pfn = d.getString("pfn");
						if (pfn.equals(oldPfn)) {
							JSONObject ym = new JSONObject();
							ym.put("ym-name", d.getString("f2"));
							ym.put("ym-value", d.getString("f3"));
							ym.put("ym-unit", d.getString("f4"));
							ym.put("ym-sr", d.getString("f5"));
							ym.put("ym-group", d.getString("f6"));
							ymList.add(ym);
						} else {
							if (value != null) {
								if (ymList != null) {
									value.put("ym", ymList);
									ymList = null;
								}
								valueList.add(index, value);
								index = index + 1;
							}
							value = new JSONObject();

							oldPfn = pfn;
							value.put("item", pfn);
							value.put("rslt", d.getString("f1"));
							value.put("expert", d.getString("f7"));

							if (!d.getString("f2").equals("")) {
								ymList = new JSONArray();
								JSONObject ym = new JSONObject();
								ym.put("ym-name", d.getString("f2"));
								ym.put("ym-value", d.getString("f3"));
								ym.put("ym-unit", d.getString("f4"));
								ym.put("ym-sr", d.getString("f5"));
								ym.put("ym-group", d.getString("f6"));
								ymList.add(ym);
							}
						}
					}
					if (ymList != null)
						value.put("ym", ymList);
					valueList.add(index, value);
					result.put("value", valueList);
					info.remove("detailList");
					info.put("result", result);
				} else if (f50.equals("杭州药敏组含计数4抬头模板(无字段名)")) {// '200005486'
					result.put("type", "17");
					JSONArray valueList = new JSONArray();
					String oldPfn = "";
					int index = 0;
					JSONObject value = null;
					JSONArray ymList = null;
					for (j = 0; j < dl.size(); j++) {
						JSONObject d = dl.getJSONObject(j);
						String pfn = d.getString("pfn");
						if (pfn.equals(oldPfn)) {
							JSONObject ym = new JSONObject();
							ym.put("ym-name", d.getString("f2"));
							ym.put("ym-sr", d.getString("f3"));
							ym.put("ym-standard", d.getString("f4"));
							ymList.add(ym);
						} else {
							if (value != null) {
								if (ymList != null) {
									value.put("ym", ymList);
									ymList = null;
								}
								valueList.add(index, value);
								index = index + 1;
							}
							value = new JSONObject();

							oldPfn = pfn;
							value.put("item", pfn);
							value.put("rslt", d.getString("f1"));

							if (!d.getString("f2").equals("")) {
								ymList = new JSONArray();
								JSONObject ym = new JSONObject();
								ym.put("ym-name", d.getString("f2"));
								ym.put("ym-sr", d.getString("f3"));
								ym.put("ym-standard", d.getString("f4"));
								ymList.add(ym);
							}
						}
					}
					if (ymList != null)
						value.put("ym", ymList);
					valueList.add(index, value);
					result.put("value", valueList);
					info.remove("detailList");
					info.put("result", result);
				} else if (f50.equals("杭州无菌1抬头模板_套餐")) {
					result.put("type", "11");// '197775464','122390209','197427618'
					JSONArray valueList = new JSONArray();
					for (int k = 0; k < dl.size(); k++) {
						JSONObject value = new JSONObject();
						JSONObject d = dl.getJSONObject(k);
						if (!d.getString("f2").equals("")) {
							value.put("item", d.getString("f1"));
							value.put("rslt", d.getString("f2"));
						} else {
							value.put("item", "");
							value.put("rslt", d.getString("f1"));
						}
						valueList.add(k, value);
					}
					result.put("value", valueList);
					info.remove("detailList");
					info.put("result", result);
				} else if (f50.equals("支原体大类20170306")) {
					result.put("type", "20"); //'119490250','126541247', '123490559'
					JSONArray valueList = new JSONArray();
					int k = 0;
					for (k = 0; k < 16; k++) {
						JSONObject d0 = dl.getJSONObject(0);
						JSONObject d1 = dl.getJSONObject(1);
						JSONObject value = new JSONObject();
						value.put("item",
								d0.getString("f" + (k + 2)).replace("\n", ""));
						value.put("rslt", d1.getString("f" + (k + 2)));
						value.put("type", "1");
						valueList.add(k, value);
					}
					for (j = 2; j < dl.size(); j++) {
						JSONObject d = dl.getJSONObject(j);
						JSONObject value = new JSONObject();
						value.put("item", d.getString("f1"));
						value.put("rslt", d.getString("f2"));
						value.put("type", "2");
						valueList.add(k, value);
					}
					result.put("value", valueList);
					info.remove("detailList");
					info.put("result", result);

				} else if (f50.equals("物体表面细菌培养20170306")
						|| f50.equals("消毒液监测V2") || f50.equals("空气培养V2")) {
					if (f50.equals("物体表面细菌培养20170306"))
						result.put("type", "41");// 120852878
					else if (f50.equals("消毒液监测V2"))
						result.put("type", "43");// 126119211
					else if (f50.equals("空气培养V2"))
						result.put("type", "44");// 126145075

					JSONArray valueList = new JSONArray();
					if (dl.size() > 0) {
						valueList.add(0, dl.getJSONObject(0).getString("f1"));
						valueList.add(1, dl.getJSONObject(0).getString("f2"));
					}
					result.put("value", valueList);
					info.remove("detailList");
					info.put("result", result);
				} else if (f50.equals("医疗保健机构消毒卫生监测20170306")) {
					result.put("type", "46"); // 102273769
					JSONArray valueList = new JSONArray();
					for (int k = 0; k < dl.size(); k++) {
						JSONObject value = new JSONObject();
						value.put("item", dl.getJSONObject(k).getString("f1"));
						value.put("rslt", dl.getJSONObject(k).getString("f2"));
						value.put(
								"standard",
								dl.getJSONObject(k).getString("f3")
										.replace("\n", " "));
						valueList.add(k, value);
					}
					result.put("value", valueList);
					info.remove("detailList");
					info.put("result", result);
				} else if (f50.equals("院感")) {
					if (f50.equals("院感"))
						result.put("type", "45");// 121303478
					JSONArray valueList = new JSONArray();
					JSONObject value = new JSONObject();
					if (dl.size() > 0) {
						value.put("item", dl.getJSONObject(0).getString("f1"));
						value.put("rslt", dl.getJSONObject(0).getString("f2"));
						value.put(
								"standard",
								dl.getJSONObject(0).getString("f3")
										.replace("\n", " "));
					}
					valueList.add(value);
					result.put("value", valueList);
					info.remove("detailList");
					info.put("result", result);
				} else if (f50.equals("院感一般细菌培养及鉴定")) {
					result.put("type", "46");// '126119249','126895791','123707815'
					JSONArray valueList = new JSONArray();
					if (dl.size() > 0) {
						for (int k = 1; k < 8; k++) {
							String v = dl.getJSONObject(0).getString("f" + k);
							if (!v.equals(""))
								valueList.add(k - 1, v);
						}
					}
					result.put("value", valueList);
					info.remove("detailList");
					info.put("result", result);
				} else {// 200005486
					result.put("type", "99");
					result.put("value", dl);
					info.put("result", result);
				}
			}
			infoList.add(i, info);
		}
		return infoList.toString();
	}
}
