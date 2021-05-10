package com.tola.easy_orders;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.internal.$Gson$Preconditions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    private Button logout;
    private Button save;
    private EditText name;
    private ListView listview;
    private Button UploadImg;

    private Uri imagUri;

    private static final  int IMAGE_REQUEST = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logout = findViewById(R.id.main_btnLogout);
        name = findViewById(R.id.main_etName);
        save = findViewById(R.id.main_btnSave);
        listview = findViewById(R.id.main_lsvName);
        UploadImg = findViewById(R.id.btnUploadImg);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(MainActivity.this,"Log out",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text_name = name.getText().toString();
                if(text_name.isEmpty()){
                    Toast.makeText(MainActivity.this,"No name entered",Toast.LENGTH_SHORT).show();
                }else{
                    FirebaseDatabase.getInstance().getReference().child("Easy Order").push().child("Name").setValue(text_name);
                    Toast.makeText(MainActivity.this,"Data was saved",Toast.LENGTH_SHORT).show();
                }
            }
        });

        ArrayList<String>  list = new ArrayList<>();
        ArrayAdapter adapter= new ArrayAdapter<String>(this,R.layout.list_item,list );
        listview.setAdapter(adapter);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("easy-order-7a26c-default-rtdb");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    list.add(snapshot.getValue().toString());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//=============================================CLOUD FIRESTOR================================================
//        //ADD AND APPEND DATA TO FIRESTORE
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        Map<String , Object> city = new HashMap<>();
//        city.put("name", "Tola");
//        city.put("city", "Phnom Penh");
//        city.put("Country", "Cambodia");
//
//        db.collection("Cities").document("JSR").set(city).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if (task.isSuccessful()){
//                    Toast.makeText(MainActivity.this, "Values is added!",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

    //    Map<String,Object> data = new HashMap<>();
//        data.put("Age",30);
//        db.collection("Cities").document("JSR").set(data, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if (task.isSuccessful()){
//                    Toast.makeText(MainActivity.this, "Values is merged!",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });


        //UPDATE DATA IN FIRESTORE
//        DocumentReference ref = FirebaseFirestore.getInstance().collection("Cities").document("JSR");
//        ref.update("Age",29);
//
//
//        //GET DATA FROM A COLLECTION
//        FirebaseFirestore.getInstance().collection("Cities").whereEqualTo("name","Tola")
//                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if(task.isSuccessful()){
//                    for(QueryDocumentSnapshot doc : task.getResult()){
//
//                        Log.d("Document",doc.getId()+ " => "+doc.getData());
//                    }
//                }
//            }
//        });
//================================================CLOUD STRORAGE==================================================

UploadImg.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        openImage();
    }
});
    }
    private String getFileExtension(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getMimeTypeFromExtension(contentResolver.getType(uri));
    }

    private  void openImage(){
        Intent intent = new Intent();
        intent.setType("image/");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == IMAGE_REQUEST){
            imagUri = data.getData();
            uploadImage();
        }
    }

    private void uploadImage(){
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Uploading...");
        pd.show();
        if(imagUri != null){
            StorageReference fileRef = FirebaseStorage.getInstance().getReference().child("uploads").child(System.currentTimeMillis() + "." + getFileExtension(imagUri));
fileRef.putFile(imagUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
    @Override
    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
        fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();
                Log.d("DownlaodUrl: ",url);
                pd.dismiss();
                Toast.makeText(MainActivity.this, "Image upload successfull", Toast.LENGTH_SHORT).show();
            }
        });
    }
});

        }
        }
}