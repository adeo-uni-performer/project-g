SELECT * FROM project_g.word_path where path like '%Main_Page%' order by count desc;
select * from project_g.word_path where word like "%page%" order by count desc;

select word,count from word_path 
order by count desc;

select count(count) from word_path
where path in (select distinct(path) from project_g.word_path)
;





