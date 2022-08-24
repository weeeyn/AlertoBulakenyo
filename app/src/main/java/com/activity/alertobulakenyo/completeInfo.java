package com.activity.alertobulakenyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class completeInfo extends AppCompatActivity {

    TextInputLayout tilContact, tilHouse, tilCity, tilBrgy, tilProvince;
    EditText etContact, etHouse;
    AutoCompleteTextView actCity, actBrgy, actProvince;
    Button btnDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_complete_info);

        tilContact = (TextInputLayout) findViewById (R.id.tilContact);
        tilHouse = (TextInputLayout) findViewById (R.id.tilHouse);
        tilCity = (TextInputLayout) findViewById (R.id.tilCity);
        tilBrgy = (TextInputLayout) findViewById (R.id.tilBrgy);
        tilProvince = (TextInputLayout) findViewById (R.id.tilProvince);

        etContact = (EditText) findViewById (R.id.etContact);
        etHouse = (EditText) findViewById (R.id.etHouse);

        actCity = (AutoCompleteTextView) findViewById (R.id.actCity);
        actBrgy = (AutoCompleteTextView) findViewById (R.id.actBrgy);
        actProvince = (AutoCompleteTextView) findViewById (R.id.actProvince);

        btnDone = (Button) findViewById (R.id.btnDone);

        String [] city = {"Angat", "Balagtas", "Baliuag", "Bocaue", "Bulacan", "Bustos", "Calumpit",
                "Doña Remedios Trinidad", "Guiguinto", "Hagonoy", "Malolos", "Marilao", "Meycauayan",
                "Norzagaray", "Obando", "Pandi", "Paombong", "Plaridel", "Pulilan", "San Ildefonso",
                "San Jose del Monte", "San Miguel", "San Rafael", "Santa Maria"};

        String [] brgyAngat = {"Banaban", "Baybay", "Binagbag", "Donacion", "Encanto", "Laog",
                "Marungko", "Niugan", "Paltok", "Pulong Yantok", "San Roque", "Santa Cruz",
                "Santa Lucia", "Santo Cristo", "Sulucan", "Taboc"};

        String [] brgyBala = {"Borol 1st", "Borol 2nd", "Dalig", "Longos", "Panginay", "Pulong Gubat",
                "San Juan", "Santol", "Wawa"};

        String [] brgyBali = {"Bagong Nayon", "Barangca", "Calantipay", "Catulinan", "Concepcion",
                "Hinukay", "Makinabang", "Matangtubig", "Pagala", "Paitan", "Piel", "Pinagbarilan",
                "Poblacion", "Sabang", "San Jose", "San Roque", "Santa Barbara", "Santo Cristo",
                "Santo Niño", "Subic", "Sulivan", "Tangos", "Tarcan", "Tiaong", "Tibag", "Tilapayong",
                "Virgen delas Flores"};

        String [] brgyBoc = {"Antipona", "Bagumbayan", "Bambang", "Batia", "Biñang 1st", "Biñang 2nd",
                "Bolacan", "Bundukan", "Bunlo", "Caingin", "Duhat", "Igulot", "Lolomboy", "Poblacion",
                "Sulucan", "Taal", "Tambobong", "Turo", "Wakas"};

        String [] brgyBul = {"Bagumbayan", "Balubad", "Bambang", "Matungao", "Maysantol", "Perez",
                "Pitpitan", "San Francisco", "San Jose", "San Nicolas", "Santa Ana", "Santa Ines",
                "Taliptip", "Tibig"};

        String [] brgyBus = {"Bonga Mayor", "Bonga Menor", "Buisan", "Camachilihan", "Cambaog",
                "Catacte", "Liciada", "Malamig", "Malawak", "Poblacion", "San Pedro", "Talampas",
                "Tanawan", "Tibagan"};

        String [] brgyCal = {"Balite", "Balungao", "Buguion", "Bulusan", "Calizon", "Calumpang",
                "Caniogan", "Corazon", "Frances", "Gatbuca", "Gugo", "Iba Este", "Iba O'Este",
                "Longos", "Meysulao", "Meyto", "Palimbang", "Panducot", "Pio Cruzcosa", "Poblacion",
                "Pungo", "San Jose", "San Marcos", "San Miguel", "Santa Lucia", "Santo Niño",
                "Sapang Bayan", "Sergio Bayan", "Sucol"};

        String [] brgyDRT = {"Bayabas", "Camachile", "Camachin", "Kabayunan", "Kalawakan",
                "Pulong Sampalok", "Sapang Bulak", "Talbak"};

        String [] brgyGui = {"Cutcut", "Daungan", "Ilang‑Ilang", "Malis", "Panginay", "Poblacion",
                "Pritil", "Pulong Gubat", "Santa Cruz", "Santa Rita", "Tabang", "Tabe", "Tiaong",
                "Tuktukan"};

        String [] brgyHag = {"Abulalas", "Carillo", "Iba", "Iba‑Ibayo", "Mercado", "Palapat",
                "Pugad", "Sagrada Familia", "San Agustin", "San Isidro", "San Jose", "San Juan",
                "San Miguel", "San Nicolas", "San Pablo", "San Pascual", "San Pedro", "San Roque",
                "San Sebastian", "Santa Cruz", "Santa Elena", "Santa Monica", "Santo Niño",
                "Santo Rosario", "Tampok", "Tibaguin"};

        String [] brgyMal = {"Anilao", "Atlag", "Babatnin", "Bagna", "Bagong Bayan", "Balayong",
                "Balite", "Bangkal", "Barihan", "Bulihan", "Bungahan", "Caingin", "Calero",
                "Caliligawan", "Canalate", "Caniogan", "Catmon", "Cofradia", "Dakila", "Guinhawa",
                "Ligas", "Liyang", "Longos", "Look 1st", "Look 2nd", "Lugam", "Mabolo", "Mambog",
                "Masile", "Matimbo", "Mojon", "Namayan", "Niugan", "Pamarawan", "Panasahan",
                "Pinagbakahan", "San Agustin", "San Gabriel", "San Juan", "San Pablo", "San Vicente",
                "Santiago", "Santisima Trinidad", "Santo Cristo", "Santo Niño", "Santo Rosario",
                "Santol", "Sumapang Bata", "Sumapang Matanda", "Taal", "Tikay"};

        String [] brgyMar = {"Abangan Norte", "Abangan Sur", "Ibayo", "Lambakin", "Lias", "Loma de Gato",
                "Nagbalon", "Patubig", "Poblacion I", "Poblacion II", "Prenza I", "Prenza II",
                "Santa Rosa I", "Santa Rosa II", "Saog", "Tabing Ilog"};

        String [] brgyMey = {"Bagbaguin", "Bahay Pare", "Bancal", "Banga", "Bayugo", "Caingin",
                "Calvario", "Camalig", "Hulo", "Iba", "Langka", "Lawa", "Libtong", "Liputan", "Longos",
                "Malhacan", "Pajo", "Pandayan", "Pantoc", "Perez", "Poblacion", "Saluysoy",
                "Saint Francis (Gasak)", "Tugatog", "Ubihan", "Zamora"};

        String [] brgyNor = {"Bangkal", "Baraka", "Bigte", "Bitungol",
                "Friendship Village Resources (FVR)", "Matictic", "Minuyan", "Partida",
                "Pinagtulayan", "Poblacion", "San Mateo", "Tigbe", "San Lorenzo (Hilltop)"};

        String [] brgyOba = {"Binuangan", "Catanghalan", "Hulo", "Lawa", "Paco", "Pag‑asa", "Paliwas",
                "Panghulo", "Salambao", "San Pascual", "Tawiran"};

        String [] brgyPan = {"Bagbaguin", "Bagong Barrio", "Baka‑bakahan", "Bunsuran I", "Bunsuran II",
                "Bunsuran III", "Cacarong Bata", "Cacarong Matanda", "Cupang", "Malibong Bata",
                "Malibong Matanda", "Manatal", "Mapulang Lupa", "Masagana", "Masuso", "Pinagkuartelan",
                "Poblacion", "Real de Cacarong", "San Roque", "Santo Niño", "Siling Bata", "Siling Matanda"};

        String [] brgyPaom = {"Binakod ", "Kapitangan", "Malumot", "Masukol", "Pinalagdan", "Poblacion",
                "San Isidro I", "San Isidro II", "San Jose", "San Roque", "San Vicente", "Santa Cruz",
                "Santo Niño", "Santo Rosario"};

        String [] brgyPla = {"Agnaya", "Bagong Silang", "Banga I", "Banga II", "Bintog", "Bulihan",
                "Culianin", "Dampol", "Lagundi", "Lalangan", "Lumang Bayan", "Parulan", "Poblacion",
                "Rueda", "San Jose", "Santa Ines", "Santo Niño", "Sipat", "Tabang"};

        String [] brgyPul = {"Balatong A", "Balatong B", "Cutcot", "Dampol I", "Dampol 2A", "Dampol 2B",
                "Dulong Malabon", "Inaon", "Longos", "Lumbac", "Paltao", "Peñabatan", "Poblacion",
                "Sta Peregrina", "Sto Cristo", "Taal", "Tabon", "Tibag", "Tinejero"};

        String [] brgySanIl = {"Akle", "Alagao", "Anyatam", "Bagong Barrio", "Basuit", "Bubulong Malaki",
                "Bubulong Munti", "Buhol na Mangga", "Bulusukan", "Calasag", "Calawitan", "Casalat",
                "Gabihan", "Garlang", "Lapnit", "Maasim", "Makapilapil", "Malipampang", "Mataas na Parang",
                "Matimbubong", "Nabaong Garlang", "Palapala", "Pasong Bangkal", "Pinaod", "Poblacion",
                "Pulong Tamo", "San Juan", "Santa Catalina Bata", "Santa Catalina Matanda", "Sapang Dayap",
                "Sapang Putik", "Sapang Putol", "Sumandig", "Telapatio", "Umpucan", "Upig"};

        String [] brgySJDM = {"Assumption", "Bagong Buhay I", "Bagong Buhay II", "Bagong Buhay III",
                "Citrus", "Ciudad Real", "Dulong Bayan", "Fatima I", "Fatima II", "Fatima III",
                "Fatima IV", "Fatima V", "Francisco Homes - Guijo", "Francisco Homes - Mulawin",
                "Francisco Homes - Narra", "Francisco Homes - Yakal", "Gaya-Gaya", "Graceville",
                "Gumaoc - Central", "Gumaoc - East", "Gumaoc - West", "Kaybanban", "Kaypian",
                "Lawang Pari", "Maharlika", "Minuyan I", "Minuyan II", "Minuyan III", "Minuyan IV",
                "Minuyan Proper", "Minuyan V", "Muzon", "Paradise III", "Poblacion", "Poblacion I",
                "San Isidro", "San Manuel", "San Martin I", "San Martin II", "San Martin III",
                "San Martin IV", "San Pedro", "San Rafael I", "San Rafael II", "San Rafael III",
                "San Rafael IV", "San Rafael V", "San Roque", "Santa Cruz I", "Santa Cruz II",
                "Santa Cruz III", "Santa Cruz IV", "Santa Cruz V", "Santo Cristo", "Santo Niño I",
                "Santo Niño II", "Sapang Palay Proper", "St. Martin de Porres", "Tungkong Mangga"};

        String [] brgySanMig = {"Bagong Pag‑asa", "Bagong Silang", "Balaong", "Balite", "Bantog",
                "Bardias", "Baritan", "Batasan Bata", "Batasan Matanda", "Biak‑na‑Bato", "Biclat",
                "Buga", "Buliran", "Bulualto", "Calumpang", "Cambio", "Camias", "Ilog‑Bulo", "King Kabayo",
                "Labne", "Lambakin", "Magmarale", "Malibay", "Maligaya", "Mandile", "Masalipit",
                "Pacalag", "Paliwasan", "Partida", "Pinambaran", "Poblacion", "Pulong Bayabas",
                "Pulong Bayabas", "Pulong Duhat", "Sacdalan", "Salacot", "Salangan", "San Agustin",
                "San Jose", "San Juan", "San Vicente", "Santa Ines", "Santa Lucia", "Santa Rita Bata",
                "Santa Rita Matanda", "Sapang", "Sibul", "Tartaro", "Tibagan", "Tigpalas"};

        String [] brgySanRaf = {"Banca‑banca", "BMA ‑ Balagtas", "Caingin", "Capihan", "Coral na Bato",
                "Cruz na Daan", "Dagat‑dagatan", "Diliman I", "Diliman II", "Libis", "Lico", "Maasim",
                "Mabalas‑balas", "Maguinao", "Maronguillo", "Paco", "Pansumaloc", "Pantubig",
                "Pasong Bangkal", "Pasong Callos", "Pasong Intsik", "Pinacpinacan", "Poblacion", "Pulo",
                "Pulong Bayabas", "Salapungan", "Sampaloc", "San Agustin", "San Roque", "Sapang Pahalang",
                "Talacsan", "Tambubong", "Tukod", "Ulingao"};

        String [] brgySanMa = {"Bagbaguin", "Balasing", "Buenavista", "Bulac", "Camangyanan", "Catmon",
                "Cay Pombo", "Caysio", "Guyong", "Lalakhan", "Mag-asawang Sapa", "Mahabang Parang",
                "Manggahan", "Parada", "Poblacion", "Pulong Buhangin", "San Gabriel", "San Jose Patag",
                "San Vicente", "Santa Clara", "Santa Cruz", "Silangan", "Tabing Bakod", "Tumana"};

        String [] province = {"Bulacan"};

        ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(completeInfo.this, R.layout.dropdown_items, city);
        actCity.setDropDownBackgroundResource(R.color.white);
        actCity.setAdapter(cityAdapter);

        ((AutoCompleteTextView)tilCity.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedCity = cityAdapter.getItem(position);

                if (selectedCity == "Angat")
                {
                    ArrayAdapter<String> brgyAdapter = new ArrayAdapter<>(completeInfo.this, R.layout.dropdown_items, brgyAngat);
                    actBrgy.setDropDownBackgroundResource(R.color.white);
                    actBrgy.setAdapter(brgyAdapter);

                    ((AutoCompleteTextView)tilBrgy.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String selectedBrgy = brgyAdapter.getItem(position);

                        }
                    });
                }
                else if (selectedCity == "Balagtas")
                {
                    ArrayAdapter<String> brgyAdapter = new ArrayAdapter<>(completeInfo.this, R.layout.dropdown_items, brgyBala);
                    actBrgy.setDropDownBackgroundResource(R.color.white);
                    actBrgy.setAdapter(brgyAdapter);

                    ((AutoCompleteTextView)tilBrgy.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String selectedBrgy = brgyAdapter.getItem(position);

                        }
                    });
                }
                else if (selectedCity == "Baliuag")
                {
                    ArrayAdapter<String> brgyAdapter = new ArrayAdapter<>(completeInfo.this, R.layout.dropdown_items, brgyBali);
                    actBrgy.setDropDownBackgroundResource(R.color.white);
                    actBrgy.setAdapter(brgyAdapter);

                    ((AutoCompleteTextView)tilBrgy.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String selectedBrgy = brgyAdapter.getItem(position);

                        }
                    });
                }
                else if (selectedCity == "Bocaue")
                {
                    ArrayAdapter<String> brgyAdapter = new ArrayAdapter<>(completeInfo.this, R.layout.dropdown_items, brgyBoc);
                    actBrgy.setDropDownBackgroundResource(R.color.white);
                    actBrgy.setAdapter(brgyAdapter);

                    ((AutoCompleteTextView)tilBrgy.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String selectedBrgy = brgyAdapter.getItem(position);

                        }
                    });
                }
                else if (selectedCity == "Bulacan")
                {
                    ArrayAdapter<String> brgyAdapter = new ArrayAdapter<>(completeInfo.this, R.layout.dropdown_items, brgyBul);
                    actBrgy.setDropDownBackgroundResource(R.color.white);
                    actBrgy.setAdapter(brgyAdapter);

                    ((AutoCompleteTextView)tilBrgy.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String selectedBrgy = brgyAdapter.getItem(position);

                        }
                    });
                }
                else if (selectedCity == "Bustos")
                {
                    ArrayAdapter<String> brgyAdapter = new ArrayAdapter<>(completeInfo.this, R.layout.dropdown_items, brgyBus);
                    actBrgy.setDropDownBackgroundResource(R.color.white);
                    actBrgy.setAdapter(brgyAdapter);

                    ((AutoCompleteTextView)tilBrgy.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String selectedBrgy = brgyAdapter.getItem(position);

                        }
                    });
                }
                else if (selectedCity == "Calumpit")
                {
                    ArrayAdapter<String> brgyAdapter = new ArrayAdapter<>(completeInfo.this, R.layout.dropdown_items, brgyCal);
                    actBrgy.setDropDownBackgroundResource(R.color.white);
                    actBrgy.setAdapter(brgyAdapter);

                    ((AutoCompleteTextView)tilBrgy.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String selectedBrgy = brgyAdapter.getItem(position);

                        }
                    });
                }
                else if (selectedCity == "Doña Remedios Trinidad")
                {
                    ArrayAdapter<String> brgyAdapter = new ArrayAdapter<>(completeInfo.this, R.layout.dropdown_items, brgyDRT);
                    actBrgy.setDropDownBackgroundResource(R.color.white);
                    actBrgy.setAdapter(brgyAdapter);

                    ((AutoCompleteTextView)tilBrgy.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String selectedBrgy = brgyAdapter.getItem(position);

                        }
                    });
                }
                else if (selectedCity == "Guiguinto")
                {
                    ArrayAdapter<String> brgyAdapter = new ArrayAdapter<>(completeInfo.this, R.layout.dropdown_items, brgyGui);
                    actBrgy.setDropDownBackgroundResource(R.color.white);
                    actBrgy.setAdapter(brgyAdapter);

                    ((AutoCompleteTextView)tilBrgy.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String selectedBrgy = brgyAdapter.getItem(position);

                        }
                    });
                }
                else if (selectedCity == "Hagonoy")
                {
                    ArrayAdapter<String> brgyAdapter = new ArrayAdapter<>(completeInfo.this, R.layout.dropdown_items, brgyHag);
                    actBrgy.setDropDownBackgroundResource(R.color.white);
                    actBrgy.setAdapter(brgyAdapter);

                    ((AutoCompleteTextView)tilBrgy.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String selectedBrgy = brgyAdapter.getItem(position);

                        }
                    });
                }
                else if (selectedCity == "Malolos")
                {
                    ArrayAdapter<String> brgyAdapter = new ArrayAdapter<>(completeInfo.this, R.layout.dropdown_items, brgyMal);
                    actBrgy.setDropDownBackgroundResource(R.color.white);
                    actBrgy.setAdapter(brgyAdapter);

                    ((AutoCompleteTextView)tilBrgy.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String selectedBrgy = brgyAdapter.getItem(position);

                        }
                    });
                }
                else if (selectedCity == "Marilao")
                {
                    ArrayAdapter<String> brgyAdapter = new ArrayAdapter<>(completeInfo.this, R.layout.dropdown_items, brgyMar);
                    actBrgy.setDropDownBackgroundResource(R.color.white);
                    actBrgy.setAdapter(brgyAdapter);

                    ((AutoCompleteTextView)tilBrgy.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String selectedBrgy = brgyAdapter.getItem(position);

                        }
                    });
                }
                else if (selectedCity == "Meycauayan")
                {
                    ArrayAdapter<String> brgyAdapter = new ArrayAdapter<>(completeInfo.this, R.layout.dropdown_items, brgyMey);
                    actBrgy.setDropDownBackgroundResource(R.color.white);
                    actBrgy.setAdapter(brgyAdapter);

                    ((AutoCompleteTextView)tilBrgy.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String selectedBrgy = brgyAdapter.getItem(position);

                        }
                    });
                }
                else if (selectedCity == "Norzagaray")
                {
                    ArrayAdapter<String> brgyAdapter = new ArrayAdapter<>(completeInfo.this, R.layout.dropdown_items, brgyNor);
                    actBrgy.setDropDownBackgroundResource(R.color.white);
                    actBrgy.setAdapter(brgyAdapter);

                    ((AutoCompleteTextView)tilBrgy.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String selectedBrgy = brgyAdapter.getItem(position);

                        }
                    });
                }
                else if (selectedCity == "Obando")
                {
                    ArrayAdapter<String> brgyAdapter = new ArrayAdapter<>(completeInfo.this, R.layout.dropdown_items, brgyOba);
                    actBrgy.setDropDownBackgroundResource(R.color.white);
                    actBrgy.setAdapter(brgyAdapter);

                    ((AutoCompleteTextView)tilBrgy.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String selectedBrgy = brgyAdapter.getItem(position);

                        }
                    });
                }
                else if (selectedCity == "Pandi")
                {
                    ArrayAdapter<String> brgyAdapter = new ArrayAdapter<>(completeInfo.this, R.layout.dropdown_items, brgyPan);
                    actBrgy.setDropDownBackgroundResource(R.color.white);
                    actBrgy.setAdapter(brgyAdapter);

                    ((AutoCompleteTextView)tilBrgy.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String selectedBrgy = brgyAdapter.getItem(position);

                        }
                    });
                }
                else if (selectedCity == "Paombong")
                {
                    ArrayAdapter<String> brgyAdapter = new ArrayAdapter<>(completeInfo.this, R.layout.dropdown_items, brgyPaom);
                    actBrgy.setDropDownBackgroundResource(R.color.white);
                    actBrgy.setAdapter(brgyAdapter);

                    ((AutoCompleteTextView)tilBrgy.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String selectedBrgy = brgyAdapter.getItem(position);

                        }
                    });
                }

                else if (selectedCity == "Plaridel")
                {
                    ArrayAdapter<String> brgyAdapter = new ArrayAdapter<>(completeInfo.this, R.layout.dropdown_items, brgyPla);
                    actBrgy.setDropDownBackgroundResource(R.color.white);
                    actBrgy.setAdapter(brgyAdapter);

                    ((AutoCompleteTextView)tilBrgy.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String selectedBrgy = brgyAdapter.getItem(position);

                        }
                    });
                }
                else if (selectedCity == "Pulilan")
                {
                    ArrayAdapter<String> brgyAdapter = new ArrayAdapter<>(completeInfo.this, R.layout.dropdown_items, brgyPul);
                    actBrgy.setDropDownBackgroundResource(R.color.white);
                    actBrgy.setAdapter(brgyAdapter);

                    ((AutoCompleteTextView)tilBrgy.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String selectedBrgy = brgyAdapter.getItem(position);

                        }
                    });
                }
                else if (selectedCity == "San Jose del Monte")
                {
                    ArrayAdapter<String> brgyAdapter = new ArrayAdapter<>(completeInfo.this, R.layout.dropdown_items, brgySJDM);
                    actBrgy.setDropDownBackgroundResource(R.color.white);
                    actBrgy.setAdapter(brgyAdapter);

                    ((AutoCompleteTextView)tilBrgy.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String selectedBrgy = brgyAdapter.getItem(position);

                        }
                    });
                }
                else if (selectedCity == "San Ildefonso")
                {
                    ArrayAdapter<String> brgyAdapter = new ArrayAdapter<>(completeInfo.this, R.layout.dropdown_items, brgySanIl);
                    actBrgy.setDropDownBackgroundResource(R.color.white);
                    actBrgy.setAdapter(brgyAdapter);

                    ((AutoCompleteTextView)tilBrgy.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String selectedBrgy = brgyAdapter.getItem(position);

                        }
                    });
                }
                else if (selectedCity == "San Miguel")
                {
                    ArrayAdapter<String> brgyAdapter = new ArrayAdapter<>(completeInfo.this, R.layout.dropdown_items, brgySanMig);
                    actBrgy.setDropDownBackgroundResource(R.color.white);
                    actBrgy.setAdapter(brgyAdapter);

                    ((AutoCompleteTextView)tilBrgy.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String selectedBrgy = brgyAdapter.getItem(position);

                        }
                    });
                }
                else if (selectedCity == "San Rafael")
                {
                    ArrayAdapter<String> brgyAdapter = new ArrayAdapter<>(completeInfo.this, R.layout.dropdown_items, brgySanRaf);
                    actBrgy.setDropDownBackgroundResource(R.color.white);
                    actBrgy.setAdapter(brgyAdapter);

                    ((AutoCompleteTextView)tilBrgy.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String selectedBrgy = brgyAdapter.getItem(position);

                        }
                    });
                }
                else if (selectedCity == "Santa Maria")
                {
                    ArrayAdapter<String> brgyAdapter = new ArrayAdapter<>(completeInfo.this, R.layout.dropdown_items, brgySanMa);
                    actBrgy.setDropDownBackgroundResource(R.color.white);
                    actBrgy.setAdapter(brgyAdapter);

                    ((AutoCompleteTextView)tilBrgy.getEditText()).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String selectedBrgy = brgyAdapter.getItem(position);

                        }
                    });
                }
            }
        });




        ArrayAdapter<String> provAdapter = new ArrayAdapter<>(completeInfo.this, R.layout.dropdown_items, province);
        actProvince.setDropDownBackgroundResource(R.color.white);
        actProvince.setAdapter(provAdapter);

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(completeInfo.this, HomeNav.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}