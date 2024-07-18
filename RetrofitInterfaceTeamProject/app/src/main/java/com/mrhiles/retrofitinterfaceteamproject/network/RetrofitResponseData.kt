package com.mrhiles.retrofitinterfaceteamproject.network

// code 종류
//  - 200 : 작업성공
// diagnosis_result 진단결과
// diagnostic_img_url 진단에 사용된 이미지
data class AiResponse(
    val diagnostic_img_url:String="",
    val diagnosis_result:String="",
    val code:String
)
data class AiResponses(
    val eye:AiResponse,
    val skin:AiResponse
)