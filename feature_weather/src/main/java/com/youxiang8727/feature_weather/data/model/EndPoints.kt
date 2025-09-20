package com.youxiang8727.feature_weather.data.model

val cityEndPoints: Map<String, EndPoints> = mapOf(
    // 北部
    "基隆市" to EndPoints(threeDays = "v1/rest/datastore/F-D0047-049", week = "v1/rest/datastore/F-D0047-051"),
    "臺北市" to EndPoints(threeDays = "v1/rest/datastore/F-D0047-061", week = "v1/rest/datastore/F-D0047-063"),
    "新北市" to EndPoints(threeDays = "v1/rest/datastore/F-D0047-069", week = "v1/rest/datastore/F-D0047-071"),
    "宜蘭縣" to EndPoints(threeDays = "v1/rest/datastore/F-D0047-001", week = "v1/rest/datastore/F-D0047-003"),
    "桃園市" to EndPoints(threeDays = "v1/rest/datastore/F-D0047-005", week = "v1/rest/datastore/F-D0047-007"),
    "新竹市" to EndPoints(threeDays = "v1/rest/datastore/F-D0047-053", week = "v1/rest/datastore/F-D0047-055"),
    "新竹縣" to EndPoints(threeDays = "v1/rest/datastore/F-D0047-009", week = "v1/rest/datastore/F-D0047-011"),
    "苗栗縣" to EndPoints(threeDays = "v1/rest/datastore/F-D0047-013", week = "v1/rest/datastore/F-D0047-015"),

    // 中部
    "臺中市" to EndPoints(threeDays = "v1/rest/datastore/F-D0047-073", week = "v1/rest/datastore/F-D0047-075"),
    "彰化縣" to EndPoints(threeDays = "v1/rest/datastore/F-D0047-017", week = "v1/rest/datastore/F-D0047-019"),
    "南投縣" to EndPoints(threeDays = "v1/rest/datastore/F-D0047-021", week = "v1/rest/datastore/F-D0047-023"),
    "雲林縣" to EndPoints(threeDays = "v1/rest/datastore/F-D0047-025", week = "v1/rest/datastore/F-D0047-027"),
    "嘉義市" to EndPoints(threeDays = "v1/rest/datastore/F-D0047-057", week = "v1/rest/datastore/F-D0047-059"),
    "嘉義縣" to EndPoints(threeDays = "v1/rest/datastore/F-D0047-029", week = "v1/rest/datastore/F-D0047-031"),

    // 南部
    "臺南市" to EndPoints(threeDays = "v1/rest/datastore/F-D0047-077", week = "v1/rest/datastore/F-D0047-079"),
    "高雄市" to EndPoints(threeDays = "v1/rest/datastore/F-D0047-065", week = "v1/rest/datastore/F-D0047-067"),
    "屏東縣" to EndPoints(threeDays = "v1/rest/datastore/F-D0047-033", week = "v1/rest/datastore/F-D0047-035"),
    "臺東縣" to EndPoints(threeDays = "v1/rest/datastore/F-D0047-037", week = "v1/rest/datastore/F-D0047-039"),
    "花蓮縣" to EndPoints(threeDays = "v1/rest/datastore/F-D0047-041", week = "v1/rest/datastore/F-D0047-043"),

    // 離島
    "澎湖縣" to EndPoints(threeDays = "v1/rest/datastore/F-D0047-045", week = "v1/rest/datastore/F-D0047-047"),
    "金門縣" to EndPoints(threeDays = "v1/rest/datastore/F-D0047-085", week = "v1/rest/datastore/F-D0047-087"),
    "連江縣" to EndPoints(threeDays = "v1/rest/datastore/F-D0047-081", week = "v1/rest/datastore/F-D0047-083")
)

data class EndPoints(
    val threeDays: String,
    val week: String
)