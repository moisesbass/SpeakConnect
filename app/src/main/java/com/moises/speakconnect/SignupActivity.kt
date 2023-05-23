package com.moises.speakconnect

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.moises.speakconnect.databinding.ActivitySignupBinding


class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var firebaseAuth: FirebaseAuth




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.signUpButton.setOnClickListener{
            val email = binding.signupEmail.text.toString()
            val password = binding.signupContrasenia.text.toString()
            val confirmPassword = binding.signupConfirmacion.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty() &&confirmPassword.isNotEmpty()){
                if (password.equals(confirmPassword)){

                    firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{
                        if (it.isSuccessful){
                            val intent = Intent(this,LoginActivity::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this, it.exception.toString(),Toast.LENGTH_SHORT).show()

                        }
                    }
                }else{
                    Toast.makeText(this, "Password does not matched",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Fields cannot be empty",Toast.LENGTH_SHORT).show()
            }
        }
        binding.loginRedirectText.setOnClickListener{
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
        }
    }
}