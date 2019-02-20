package com.app.adarsh.offersbymall;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.adarsh.offersbymall.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import static android.R.attr.scaleHeight;
import static android.R.attr.scaleWidth;
import static android.R.attr.width;
import static com.google.android.gms.analytics.internal.zzy.o;
import static com.google.android.gms.analytics.internal.zzy.t;

public class ShopholderRegister extends AppCompatActivity {

    Context mContext;

    private EditText mName, mEmail, mPhoneNo, mShopNo, mOffer, mShopName;
    private Button mButton, mUploadImage;
    private String email="", name="", password="", phoneNo="", uId="", shopNo="", offer="", city="", mall="", category="", shopName="",offerCost="";
    private Spinner mSpinner_city, mSpinner_malls, mSpinner_category;
    private String shopPhoto="";
    private StorageReference mStorageReference;
    private static final int GALLREY_INTENT = 1;
    private ProgressDialog mProgress;
    private int c = 0;
    private Uri uri;
    private Uri downLoadUri;
    private FirebaseUser user;
    private String uriInString="";
    private ActionBar actionBar;
    private RequestQueue requestQueue;


    private EditText mOffer2,mOffer3;String offer2="",offer3="";
    private Button mUpload2,mUpload3;
    String img2="",img3="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopholder_register);

        requestQueue = Volley.newRequestQueue(this);
        mContext=this;

        mName = (EditText) findViewById(R.id.name);
        mEmail = (EditText) findViewById(R.id.email);
        mPhoneNo = (EditText) findViewById(R.id.phoneNo);
        mShopNo = (EditText) findViewById(R.id.shopNo);
        mShopName = (EditText) findViewById(R.id.shopName);
        mOffer = (EditText) findViewById(R.id.offerDetails);
        mOffer2 = (EditText) findViewById(R.id.offerDetails2);
        mOffer3 = (EditText) findViewById(R.id.offerDetails3);
        mButton = (Button) findViewById(R.id.sumbmit);
        mSpinner_city = (Spinner) findViewById(R.id.city_spinner);
        mSpinner_malls = (Spinner) findViewById(R.id.malls_spinner);
        mSpinner_category = (Spinner) findViewById(R.id.category_spinner);
        mUploadImage = (Button) findViewById(R.id.imageupload);

        mUpload2=(Button)findViewById(R.id.imageupload2);
        mUpload3=(Button)findViewById(R.id.imageupload3);

        mStorageReference = FirebaseStorage.getInstance().getReference();
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        mProgress = new ProgressDialog(this);
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            uId = user.getUid();
        }



        getSupportActionBar().setTitle("Vendor Registration");


        ArrayAdapter<CharSequence> CityAdapter = ArrayAdapter.createFromResource(this, R.array.Malls_name, android.R.layout.simple_spinner_item);
        CityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> MallsAdapter = ArrayAdapter.createFromResource(this, R.array.cityMalls_name, android.R.layout.simple_spinner_item);
        MallsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> CategoryAdapter = ArrayAdapter.createFromResource(this, R.array.Category_Shops, android.R.layout.simple_spinner_item);
        CategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner_city.setAdapter(CityAdapter);
        mSpinner_malls.setAdapter(MallsAdapter);
        mSpinner_category.setAdapter(CategoryAdapter);

        mSpinner_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                city = String.valueOf(parent.getItemAtPosition(position));
                Log.d("CodeKamp", "city:" + city);
                //  mSpinner_malls.setEnabled(true);
                if (city.equalsIgnoreCase("NEW DELHI")) {
                    ArrayAdapter<CharSequence> MallsAdapter = ArrayAdapter.createFromResource(ShopholderRegister.this, R.array.newdelhi_malls, android.R.layout.simple_spinner_item);
                    MallsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mSpinner_malls.setAdapter(MallsAdapter);
                }
                if (city.equalsIgnoreCase("GHAZIABAD")) {
                    ArrayAdapter<CharSequence> MallsAdapter = ArrayAdapter.createFromResource(ShopholderRegister.this, R.array.ghaziabad_malls, android.R.layout.simple_spinner_item);
                    MallsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mSpinner_malls.setAdapter(MallsAdapter);
                }
                if (city.equalsIgnoreCase("GURGAON")) {
                    ArrayAdapter<CharSequence> MallsAdapter = ArrayAdapter.createFromResource(ShopholderRegister.this, R.array.gurgaon_mall, android.R.layout.simple_spinner_item);
                    MallsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mSpinner_malls.setAdapter(MallsAdapter);
                }
                if (city.equalsIgnoreCase("NOIDA")) {
                    ArrayAdapter<CharSequence> MallsAdapter = ArrayAdapter.createFromResource(ShopholderRegister.this, R.array.noida_malls, android.R.layout.simple_spinner_item);
                    MallsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mSpinner_malls.setAdapter(MallsAdapter);
                }
                if (city.equalsIgnoreCase("FARIDABAD")) {
                    ArrayAdapter<CharSequence> MallsAdapter = ArrayAdapter.createFromResource(ShopholderRegister.this, R.array.faridabad_malls, android.R.layout.simple_spinner_item);
                    MallsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mSpinner_malls.setAdapter(MallsAdapter);
                }
                if (city.equalsIgnoreCase("MEERUT")) {
                    ArrayAdapter<CharSequence> MallsAdapter = ArrayAdapter.createFromResource(ShopholderRegister.this, R.array.meerut_malls, android.R.layout.simple_spinner_item);
                    MallsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mSpinner_malls.setAdapter(MallsAdapter);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mSpinner_malls.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                mall = (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mSpinner_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category = (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                offer2=mOffer2.getText().toString();
                offer3=mOffer3.getText().toString();


                name = mName.getText().toString();
                    email = mEmail.getText().toString();
                    phoneNo = mPhoneNo.getText().toString();
                    shopNo = mShopNo.getText().toString();
                    shopName = mShopName.getText().toString();
                    offer = mOffer.getText().toString();
                    // UserDetails userDetail=new UserDetails();


                    if (name.matches("") || email.matches("") || phoneNo.matches("")
                            || shopName.matches("") || shopNo.matches("") || city.matches("") || mall.matches("") || offer.matches("")
                            || category.matches("") || uriInString.matches("") || offer2.matches("") || offer3.matches(""))
                        Toast.makeText(ShopholderRegister.this, "Please fill all the details first!", Toast.LENGTH_LONG).show();
                    else {
                        final ProgressDialog pg=new ProgressDialog(mContext);
                        pg.setMessage("Contacting server...");
                        pg.show();
                        String url = "http://offersbymall.com/test/ace.php";
                        StringRequest jsonObjRequest = new StringRequest(Request.Method.POST,
                                url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        pg.dismiss();
                                        mButton.setEnabled(false);
                                        Snackbar.make(findViewById(android.R.id.content),"Confirmation email will be sent shortly!!",Snackbar.LENGTH_SHORT).show();
                                        final Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                finish();
                                            }
                                        }, 2000);
                                    }
                                }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                pg.dismiss();
                                Toast.makeText(getBaseContext(),"Failed, try again later!!",Toast.LENGTH_LONG).show();
                                VolleyLog.d("volley", "Error: " + error.getMessage());
                                error.printStackTrace();
                            }
                        }) {

                            @Override
                            public String getBodyContentType() {
                                return "application/x-www-form-urlencoded; charset=UTF-8";
                            }

                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> params = new HashMap<String, String>();
                                params.put("name", name);
                                params.put("mall_name", mall);
                                params.put("phone", phoneNo);
                                params.put("shop_name", shopName);
                                params.put("shop_number", shopNo);
                                params.put("email", email);
                                params.put("categ", category);
                                params.put("image", uriInString);
                                params.put("offer_cost","");
                                params.put("offer_image",uriInString);
                                params.put("offer_name",offer);
                                params.put("offer2_cost","");
                                params.put("offer2_image",img2);
                                params.put("offer2_name",offer2);
                                params.put("offer3_cost","");
                                params.put("offer3_image",img3);
                                params.put("offer3_name",offer3);
                                params.put("submit"," ");
                                return params;
                            }
                        };
                        requestQueue.add(jsonObjRequest);
                    }

            }
        });
        mUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                Log.d("CodeKamp", "image");
                startActivityForResult(intent, GALLREY_INTENT);

            }
        });
        mUpload2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                Log.d("CodeKamp", "image");
                startActivityForResult(intent, 2);

            }
        });
        mUpload3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                Log.d("CodeKamp", "image");
                startActivityForResult(intent, 3);

            }
        });

        ActivityCompat.requestPermissions(ShopholderRegister.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},
                1);
    }
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(getBaseContext(),"Permission Granted!",Toast.LENGTH_SHORT).show();
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(getBaseContext(), "Permission denied to read your External storage, Exiting!", Toast.LENGTH_SHORT).show();
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }, 2000);
                }
                return;
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mProgress.setMessage("Uploading...");
        mProgress.show();
        if (requestCode == GALLREY_INTENT && resultCode == RESULT_OK) {
            uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                bitmap=process(bitmap);
                uriInString=BitMapToString(bitmap);
                mProgress.hide();
                Snackbar.make(findViewById(android.R.id.content),"Image attached!!",Snackbar.LENGTH_SHORT).show();
                mUploadImage.setText("Change offer image");
            }catch (Exception e){
                Log.e("image",e.toString());
                Toast.makeText(getBaseContext(),e.toString(),Toast.LENGTH_LONG).show();
                mProgress.hide();
                mUploadImage.setText("Attach offer image");
            }
        }
        else  if (requestCode == 2 && resultCode == RESULT_OK) {
            Uri imgUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imgUri);
                bitmap=process(bitmap);
                img2 = BitMapToString(bitmap);
                mProgress.hide();
                Snackbar.make(findViewById(android.R.id.content), "Image attached!!", Snackbar.LENGTH_SHORT).show();
                mUpload2.setText("Change offer image");
            } catch (Exception e) {
                Log.e("image", e.toString());
                Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();
                mProgress.hide();
                mUpload2.setText("Attach offer image");
            }
        } else  if (requestCode == 3 && resultCode == RESULT_OK) {
            Uri imgUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imgUri);
                bitmap=process(bitmap);
                img3 = BitMapToString(bitmap);
                mProgress.hide();
                Snackbar.make(findViewById(android.R.id.content), "Image attached!!", Snackbar.LENGTH_SHORT).show();
                mUpload3.setText("Change offer image");
            } catch (Exception e) {
                Log.e("image", e.toString());
                Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();
                mProgress.hide();
                mUpload3.setText("Attach offer image");
            }
        }
        if(mProgress.isShowing())mProgress.hide();
    }
    public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,80, baos);
        byte [] arr=baos.toByteArray();
        String result=Base64.encodeToString(arr, Base64.DEFAULT);
        return result;
    }

    public Bitmap process(Bitmap bitmap){
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        Matrix matrix = new Matrix();
        float scaleWidth = ((float) 170) / width;
        float scaleHeight = ((float) 120) / height;
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        return resizedBitmap;
    }

    public int getc() {
        return c;
    }

    public void seturi(Uri uri) {
        this.downLoadUri = uri;
    }

    public Uri get_Uri() {
        return downLoadUri;

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mProgress.dismiss();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
