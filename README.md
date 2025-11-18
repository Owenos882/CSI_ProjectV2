🔍 CSI Project V2  
**Java 17 | Swing GUI | Evidence & Suspect Analysis System**

CSI Project V2 is an interactive Java Swing application used to simulate a crime scene investigation workflow.  
Users can load suspects, load evidence, compare findings, and ultimately convict (or wrongly convict!) a suspect based on evidence matches.

This version includes a full analysis workspace with:
- Suspect list panel  
- Evidence board panel  
- Automated matching engine  
- Random guilty suspect each run  
- Conviction system  
- Professional dashboard UI  

---

## 🚀 Features

### 🧑‍🤝‍🧑 Manage Suspects
- Load suspects from CSV  
- View full details  
- Inspect individual suspects  
- Each suspect includes:
  - Name  
  - Age  
  - Type (Local, External, Known Criminal)  
  - Alibi  
  - Hidden *guilty* flag assigned randomly at runtime  

---

### 🧾 Manage Evidence
- Load evidence from CSV  
- View table of all evidence  
- Inspect individual items  
- Evidence supports:
  - Type (Fingerprint, DNA, CCTV…)  
  - Description  
  - Location  
  - Relevance score  

---

### 🔎 Analyze Case
A full analysis workspace including:

#### ✔ Suspect Panel  
Selectable table of suspects.

#### ✔ Evidence Board  
Selectable comparison panel with all evidence.

#### ✔ Matching System  
Each evidence item can match a suspect based on:
- Type weighting  
- Relevance strength  
- Random guilty assignment  
- Logic tuned so only ONE suspect can realistically reach the required match threshold

#### ✔ Result Output  
- Shows match count  
- Shows confidence percentage  
- Allows conviction only when match count ≥ 3

#### ✔ Conviction System  
If the user convicts:
- Correct suspect → “Guilty!” message  
- Incorrect suspect → “Wrong Suspect!” message  
