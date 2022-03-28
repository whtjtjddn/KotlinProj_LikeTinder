package com.example.kotlinproj_liketinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth //Java에서는 FirebaseAuth.getInstance()와 같은 코드

        initLoginButton()
        initSignUpButton()

    }


    private fun initLoginButton() {
        val loginButton = findViewById<Button>(R.id.loginButton)
        loginButton.setOnClickListener {
            val email = getInputEmail()
            val password = getInputPassword()

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    //로그인이 성공적으로 이루어졌다면
                    if (task.isSuccessful) {
                        finish()
                    }
                    //아니라면
                    else {
                        Toast.makeText(this, "로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
    //회원가입 버튼
    private fun initSignUpButton() {
        val signUpButton = findViewById<Button>(R.id.signUpButton)
        signUpButton.setOnClickListener {
            val email = getInputEmail()
            val password = getInputPassword()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this){ task ->
                    if(task.isSuccessful){
                        Toast.makeText(this, "회원가입에 성공하였습니다. 로그인 버튼을 눌러 로그인 해주세요.", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Toast.makeText(this,"회원가입에 실패하였습니다.", Toast.LENGTH_SHORT).show()
                    }

                }
        }
    }

    private fun getInputEmail(): String {
        return findViewById<EditText>(R.id.emailEditText).text.toString()
    }

    private fun getInputPassword(): String {
        return findViewById<EditText>(R.id.passwordEditText).text.toString()
    }
}