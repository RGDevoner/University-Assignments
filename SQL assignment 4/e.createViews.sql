Create View Actor As
Select  distinct person_id, gender, name
From Movie_Cast

Create View CrewMember As
Select  distinct person_id,gender, name
From Movie_Crew

Create View Person As
Select  distinct C.person_id,C.gender,C.name
From Movie_Crew C
JOIN Movie_Cast CA ON C.person_id= CA.person_id

