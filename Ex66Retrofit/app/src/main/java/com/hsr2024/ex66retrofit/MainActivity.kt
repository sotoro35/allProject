package com.hsr2024.ex66retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.hsr2024.ex66retrofit.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {

    // HTTP 통신 작업을 위한 Library
    //1. OkHttp - 처음 만들어진 것이고 oracle 이 보유
    //2. Retrofit - OkHttp를 기반으로 개량한 라이브러리 - square 라는 회사 보유[가장 많이 사용됨], json에 특화되어 있음. 현재는 version 2
    //3. Volley - Google 에서 제작. 업데이트 지원정책이 오락가락함.. 짜증

    // 3개 중 가장 많이 사용되는 Retrofit 사용 - 라이브러리 적용

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btn1.setOnClickListener { clickBtn1() }
        binding.btn2.setOnClickListener { clickBtn2() }
        binding.btn3.setOnClickListener { clickBtn3() }
        binding.btn4.setOnClickListener { clickBtn4() }
        binding.btn5.setOnClickListener { clickBtn5() }
        binding.btn6.setOnClickListener { clickBtn6() }
        binding.btn7.setOnClickListener { clickBtn7() }
        binding.btn8.setOnClickListener { clickBtn8() }

    }
    private fun clickBtn8(){
        //서버의 응답을 그냥 글씨로 받아오기
        //1. 레트로핏 객체 -- GsonConverter는 json만 처리 가능함.. 문자열을 받으려면 ScalarsConverter 필요 - 추가 라이브러리 필요
        val builder:Retrofit.Builder= Retrofit.Builder()
        builder.baseUrl("http://ruaris.dothome.co.kr")
        builder.addConverterFactory(ScalarsConverterFactory.create())
        val retrofit= builder.build()

        val retrofiService= retrofit.create(RetrofiService::class.java)
        retrofiService.getBoardToString().enqueue(object :Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                val s:String?= response.body() ?: return
                binding.tv.text= s
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Toast.makeText(this@MainActivity, "fail: ${t.message}", Toast.LENGTH_SHORT).show()
            }

        })

    }


    private fun clickBtn7(){
        // GET 방식으로 서버에 있는 json array 를 읽어와서 곧바로 리스트로 파싱하여 받기
        val retrofitService= RetrofitHelper.getRetrofitInstance().create(RetrofiService::class.java)
        retrofitService.getBoardArray().enqueue(object :Callback<List<BoardItem>>{
            override fun onResponse(
                call: Call<List<BoardItem>>,
                response: Response<List<BoardItem>>
            ) {
                // 응답 결과 body 데이터 리스트
                val itemLIst: List<BoardItem> = response.body()!!
                // 아이템 리스트를 보여주는 리사이클러뷰를 구현해야 하지만.. 시간상..
                val builder= StringBuilder()
                for (item in itemLIst){
                    builder.append("${item.name} : ${item.msg} \n")
                }
                binding.tv.text= builder.toString()
            }


            override fun onFailure(call: Call<List<BoardItem>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "error: ${t.message}", Toast.LENGTH_SHORT).show()
            }

        })

    }
    private fun clickBtn6(){
        // POST 방식으로 개별 데이터를 전송하고 응답 받아보기
        var name= "손흥민"
        var message= "토트넘 핫스퍼"

        val retrofit= RetrofitHelper.getRetrofitInstance()
        val retrofitService= retrofit.create(RetrofiService::class.java)
        val call= retrofitService.postMethodTest2(name, message)

        call.enqueue(object : Callback<BoardItem>{
            override fun onResponse(call: Call<BoardItem>, response: Response<BoardItem>) {
                val item = response.body()
                item?.apply { binding.tv.text= "$name >> $msg" }
            }

            override fun onFailure(call: Call<BoardItem>, t: Throwable) {
                Toast.makeText(this@MainActivity, "error: ${t.message}", Toast.LENGTH_SHORT).show()
            }

        })


    }
    private fun clickBtn5(){
        // POST 방식으로 BoardItem 객체를 전송하고 응답 받아보기

        val retrofit= RetrofitHelper.getRetrofitInstance()
        val retrofiService= retrofit.create(RetrofiService::class.java)

        //서버로 보낼 데이터를 가진 BoardItem 객체
        val item:BoardItem = BoardItem("lee","Good afternoon")
        val call= retrofiService.postMethodTest(item)

        call.enqueue(object :Callback<BoardItem>{
            override fun onResponse(call: Call<BoardItem>, response: Response<BoardItem>) {
                val item= response.body()
                item?.apply { binding.tv.text= "$name , $msg" }
            }

            override fun onFailure(call: Call<BoardItem>, t: Throwable) {
                Toast.makeText(this@MainActivity, "error: ${t.message}", Toast.LENGTH_SHORT).show()
            }

        })

    }

    private fun clickBtn4(){
        // GET 방식으로 데이터를 한방에 전달하고 응답 받아보기

        //1. retrofit 객체 생성
        val retrofit = RetrofitHelper.getRetrofitInstance()
        //2. 3 추상메소드 설계 및 인터페이스 생성
        val retrofiService= retrofit.create(RetrofiService::class.java)
        //4. 추상메소드 호출하면서 값 전달
        // 전달할 데이터들의 식별자와 값들을 한방에 Map으로 넣고 전달
        val data:MutableMap<String,String> = mutableMapOf()
        //data.put("name","Hong")
        data["name"]= "Hong"
        data["msg"]= "Nice Retrofit."

        val call:Call<BoardItem> = retrofiService.getMethodTest2(data)

        //5. 네트워크 작업
        call.enqueue(object :Callback<BoardItem>{
            override fun onResponse(call: Call<BoardItem>, response: Response<BoardItem>) {
                val item= response.body()
                item?.apply {
                    binding.tv.text = "$name ** $msg "
                }
            }

            override fun onFailure(call: Call<BoardItem>, t: Throwable) {
                Toast.makeText(this@MainActivity, "실패: ${t.message}", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun clickBtn3(){
        // GET 방식으로 데이터를 전달하고 응답 받아보기..

        //서버에 보낼 데이터
        var name= "홍길동"
        var message= "안녕하세요."

        // 레트로핏 5단계 작업
        // 1.Retrofit 객체 생성 - 매번 4줄씩 짜증... 그래서 별도의 class를 설계
        val retrofit: Retrofit = RetrofitHelper.getRetrofitInstance()

        //2. 원하는 작업 명세.. 인터페이스와 추상메소드

        //3. 인터페이스 객체 생성
        val retrofiService:RetrofiService = retrofit.create(RetrofiService::class.java)

        //4. 명세 중에서 원하는 작업을 해주는 코드를 만들어주는 메소드 호출
        val call:Call<BoardItem> = retrofiService.getMethodTest(name,message)

        //5. 네트워크 작업 실행
        call.enqueue(object : Callback<BoardItem>{
            override fun onResponse(call: Call<BoardItem>, response: Response<BoardItem>) {
                val item:BoardItem? = response.body()
                item?.apply {
                    binding.tv.text= "$name ~ $message"
                }
            }

            override fun onFailure(call: Call<BoardItem>, t: Throwable) {
                Toast.makeText(this@MainActivity, "error: ${t.message}", Toast.LENGTH_SHORT).show()
            }

        })








    }

    private fun clickBtn2(){
        // 경로를 1번처럼 고정하지 않고.. 요청할때 전달하여 응답받기

        // 레트로핏 5단계 작업
        // 1.레트로핏 객체 생성
        val builder: Retrofit.Builder = Retrofit.Builder()
        builder.baseUrl("http://ruaris.dothome.co.kr")
        builder.addConverterFactory(GsonConverterFactory.create())
        val retrofit: Retrofit= builder.build() // 빌더한테 위에있는 설정대로 빌드해달라는 의미

        // 2. 명세 작성.. 인터페이스.. 추상메소드..

        // 3. 인터페이스 객체 생성
        val retrofiService:RetrofiService = retrofit.create(RetrofiService::class.java)

        //4. 명세 중에서 원하는 작업의 메소드를 호출하면.. 작업용 객체가 리턴되어 옴
        val call:Call<BoardItem> = retrofiService.getBoardJsonBypath("04Retrofit","board.json")

        //5. 네트워크 작업 실행
        call.enqueue(object : Callback<BoardItem>{
            override fun onResponse(call: Call<BoardItem>, response: Response<BoardItem>) {
                // 응답된 json을 Gson을 이용하여 BoardItem객체로 자동 파싱한 결과 body값 받기
                val item:BoardItem?= response.body()
                item?.apply {
                    binding.tv.text= "이름:${this.name}  메세지:${msg}"
                }
            }

            override fun onFailure(call: Call<BoardItem>, t: Throwable) {
                Toast.makeText(this@MainActivity, "error: ${t.message}", Toast.LENGTH_SHORT).show()
            }

        })

    }

    private fun clickBtn1(){
        // 단순하게 GET방식으로 json 파일을 읽어오기
        // retrofit library를 이용하면.. 서버에서 json 문자열을 읽어와서 분석하여 Item객체로 곧바로 파싱하여 받을 수 있음.


        // Retrofit 작업 5단계
        //1. Retrofit 객체 생성
        val buider: Retrofit.Builder = Retrofit.Builder()
        buider.baseUrl("http://ruaris.dothome.co.kr")
        buider.addConverterFactory(GsonConverterFactory.create())
        val retrofit: Retrofit = buider.build()

        //2. 레트로핏 객체가 작성해줬으면 하는 네트워크 작업에 대한 명세.. 인터페이스(추상메소드만 가지는 클래스: RetrofitService)

        //3. 위에서 설계한 명세 RetrofitService 인터페이스를 객체로 생성
        val retrofitService:RetrofiService = retrofit.create(RetrofiService::class.java)

        //4. 명세서 안에서 원하는 기능 코드를 호출(사용) - 이때 네트워크 작업을 하는 것이 아님!!
        val call:Call<BoardItem> = retrofitService.getBoardJson()

        //5. 네트워크 작업 실행
        // call.execute() // 동기식 - 권장하지 않음.
        call.enqueue(object : Callback<BoardItem>{ //enqueue 복수의 작업이 순서대로 진행되는것.. 스레드는 각자 속도가 다르므로..
            override fun onResponse(call: Call<BoardItem>, response: Response<BoardItem>) {
                // 두번째 파라미터 response : 응답결과를 받아온 객체
                val item: BoardItem?= response.body()
                if (item!=null) binding.tv.text= "${item.name} : ${item.msg}"
            }

            override fun onFailure(call: Call<BoardItem>, t: Throwable) {
                Toast.makeText(this@MainActivity, "실패: ${t.message}", Toast.LENGTH_SHORT).show()
            }

        })


    }


}