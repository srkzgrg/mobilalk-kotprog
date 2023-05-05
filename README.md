> Mobil alkalmazásfejlesztés
<br>Online telefonvásárlás
<br>Készítette: Sárközi Gergő
<br>srkzgrg@outlook.com

# Online telefonvásárlás

## 1. Összefogaló

Egy telefonokat árusító Android alkalmazásról készült a projektmunka. Az alkalmazásban bejelentkezés vagy regisztráció után lehetőség van telefonokat böngészni. A kiválasztott készüléket vagy készülékeket lehetőség van kosárba tenni, majd egy kiszállítási cím megadása után leadni a rendelést. A korábbi rendeléseket is lehetőség van megtekinteni.

## 2. Projektről

### 2.1 Funkcionális követelmények

1. Regisztráció
	- Szükséges adatok: név, e-mail, jelszó.
	- A jelszónak legalább 6 karakter hosszúnak kell lennie.
	- Csak helyes e-mail formátummal lehet regisztrálni.

2. Bejelentkezés
	- Szükséges adatok: e-mail, jelszó.
	- Az autentikáció Firebase segítségével történik.
	
3. Termékek
	- A termékek oldalon bejelentkezés után lehetőség van kosárhoz adni termékeket.
	- Egy terméket többször is hozzá lehet adni a kosárhoz, ezzel növelve a mennyiséget.
	- A termékek képei FireStore-ból töltődnek le.
	- Az oldal tetején a kereső ikonra kattintva lehet model alapján keresni.
	
3. Kosár
	- Lehetőség van a termékek darabszámának növelésére, illetve csökkentésére.
	- Láthatjuk a termékek összárát.
	- A rendelés leadáshoz meg kell adnunk:
		- Szállítási címet
		- Telefonszámot
	- Sikeres rendelés leadást követően értesítést kapunk.
	- Ki és bejelentkezés után is megmarad a kosár tartalma
	
3. Rendelések
	- Láthatjuk az összes korábbi rendelésünket.
	- Meg tudjuk tekinteni a végösszeget, illetve a rendelés dátumát

		
## 2. Fejlesztés

### 2.1 Munkakörnyezet:

- MacBook Air M1 (CPU: Apple M1, RAM: 8 GB)
- Asztali PC (CPU: Ryzen FX 8300, RAM: 8 GB, GPU: RADEON R7)

### 2.2 Fejlesztéshez használt eszközök:

- Andorid Studio
- Firebase
- Emulator
- Git (Github)

### 2.2 Tesztelési környezet:

- Pixel 3a XL (API 31)
- Pixel 3 XL (API 31)
