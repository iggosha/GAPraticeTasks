-- 1 Выдайте всю информацию о спортсменах из таблицы sportsman.
SELECT *
FROM t_sportsman;

--Выдайте наименование и мировые результаты по всем соревнованиям.
SELECT competition_name, world_record
FROM t_competition
         JOIN trainee.t_competitions_disciplines tcd on t_competition.competition_id = tcd.competition_id
         JOIN trainee.t_discipline td on tcd.discipline_id = td.discipline_id
WHERE set_date = hold_date;

--ёВыберите имена всех спортсменов, которые родились в 1990 году.
SELECT *
FROM t_sportsman
WHERE year_of_birth = 1990;

-- 4 Выберите наименование и мировые результаты по всем соревнованиям, установленные 12-05-2010 или 15-05-2010.
SELECT competition_name, world_record
FROM t_competition
         JOIN trainee.t_competitions_disciplines tcd on t_competition.competition_id = tcd.competition_id
         JOIN trainee.t_discipline td on tcd.discipline_id = td.discipline_id
WHERE (set_date = '2010-05-12' OR set_date = '2010-05-15');

-- 5 Выберите дату проведения всех соревнований, которые проводились в Москве и полученные на них результаты равны 10 секунд.
SELECT hold_date
FROM t_competition
         JOIN trainee.t_result tr on t_competition.competition_id = tr.competition_id
WHERE city = 'Москва'
  AND (result >= 10 AND result < 11);

-- 6 Выберите имена всех спортсменов, у которых персональный рекорд не равен 25 с.
SELECT sportsman_name
FROM t_sportsman
WHERE NOT EXISTS(SELECT *
                 FROM t_sportsman_record
                 WHERE record_value >= 25
                   AND record_value < 26
                   AND t_sportsman.sportsman_id = t_sportsman_record.sportsman_id);

-- 7 Выберите названия всех соревнований, у которых мировой рекорд равен 15 с и дата установки рекорда не равна 12-02-2015.S
SELECT competition_name
FROM t_competition
         JOIN trainee.t_competitions_disciplines tcd on t_competition.competition_id = tcd.competition_id
         JOIN trainee.t_discipline td on tcd.discipline_id = td.discipline_id
WHERE td.world_record >= 15
  AND td.world_record < 16
  AND td.set_date != '2015-02-12';

-- 8 Выберите города проведения соревнований, где результаты принадлежат множеству {13, 25, 17, 9}.
SELECT DISTINCT city
FROM t_competition
         JOIN t_result on t_competition.competition_id = t_result.competition_id
    AND EXISTS(SELECT t_result.competition_id
               FROM t_result
               WHERE (result >= 13 AND result < 14)
                  OR (result >= 25 AND result < 26)
                  OR (result >= 17 AND result < 18)
                  OR (result >= 9 AND result < 10));

-- 9 Выберите имена всех спортсменов, у которых год рождения 2000 и разряд не принадлежит множеству {3, 7, 9}.
SELECT sportsman_name
FROM t_sportsman
WHERE year_of_birth = 2000
  AND rank NOT IN (3, 7, 9);

-- 10 Выберите дату проведения всех соревнований, у которых город проведения начинается с буквы "М".
SELECT hold_date
FROM t_competition
WHERE city LIKE 'М%';

-- 11 Выберите имена всех спортсменов, у которых имена начинаются с буквы "М" и год рождения не заканчивается на "6".
SELECT sportsman_name
FROM t_sportsman
WHERE sportsman_name LIKE 'M%'
  AND RIGHT(CAST(year_of_birth AS VARCHAR), 1) != '6';

-- 12 Выберите наименования всех соревнований, у которых в названии есть слово "международные".
SELECT competition_name
FROM t_competition
WHERE competition_name LIKE '%международные%';
-- WHERE competition_name LIKE '%мира%';

-- 13 Выберите годы рождения всех спортсменов без повторений.
SELECT DISTINCT year_of_birth
FROM t_sportsman;

-- 14 Найдите количество результатов, полученных 12-05-2014.
SELECT COUNT(*)
FROM t_discipline
WHERE set_date = '2014-05-12';

-- 15 Вычислите максимальный результат, полученный в Москве.
SELECT MAX(result)
FROM t_result
         JOIN trainee.t_competition tc on t_result.competition_id = tc.competition_id
WHERE city = 'Москва';
-- min?

-- 16 Вычислите минимальный год рождения спортсменов, которые имеют 1 разряд
SELECT MIN(year_of_birth)
FROM t_sportsman
WHERE rank = 1;

-- 17 Определите имена спортсменов, у которых личные рекорды совпадают с результатами, установленными 12-04-2014.
SELECT sportsman_id
FROM t_sportsman_record,
     t_discipline
WHERE record_value = world_record;

-- 18 Выведите наименования соревнований, у которых дата установления мирового рекорда совпадает с датой проведения соревнований в - Москве 20-04-2015.
SELECT competition_name
FROM t_competition
         JOIN trainee.t_competitions_disciplines tcd on t_competition.competition_id = tcd.competition_id
         JOIN trainee.t_discipline td on tcd.discipline_id = td.discipline_id
WHERE set_date = hold_date;

-- 19 Вычислите средний результат каждого из спортсменов.
SELECT AVG(result)
FROM t_result;

-- Выведите годы рождения спортсменов, у которых результат, показанный в Москве выше среднего по всем спортсменам.
SELECT year_of_birth
FROM t_sportsman
         JOIN trainee.t_sportsman_record tsr on t_sportsman.sportsman_id = tsr.sportsman_id
         JOIN trainee.t_discipline td on tsr.discipline_id = td.discipline_id
         JOIN trainee.t_competitions_disciplines tcd on td.discipline_id = tcd.discipline_id
         JOIN trainee.t_competition tc on tcd.competition_id = tc.competition_id;
--
--...;

-- Выведите имена всех спортсменов, у которых год рождения больше, чем год установления мирового рекорда, равного 12 с.

-- Выведите список спортсменов в виде 'Спортсмен ' ['имя спортсмена'] 'показал результат' ['результат'] 'в городе' ['город']

-- Выведите имена всех спортсменов, у которых разряд ниже среднего разряда всех спортсменов, родившихся в 2000 году.
SELECT sportsman_name
FROM t_sportsman
WHERE rank < (SELECT AVG(rank)
              FROM t_sportsman
              WHERE year_of_birth = 2000);

-- Выведите данные о спортсменах, у которых персональный рекорд совпадает с мировым.
SELECT t_sportsman.sportsman_id, sportsman_name, rank, year_of_birth, country
FROM t_sportsman
         JOIN trainee.t_sportsman_record tsr on t_sportsman.sportsman_id = tsr.sportsman_id
         JOIN trainee.t_discipline td on tsr.discipline_id = td.discipline_id
WHERE record_value = world_record;
--

-- Определите количество участников с фамилией Иванов, которые участвовали в соревнованиях с названием, содержащим слово 'Региональные'.
SELECT sportsman_name
FROM t_sportsman
         JOIN trainee.t_result tr on t_sportsman.sportsman_id = tr.sportsman_id
         JOIN trainee.t_competition tc on tr.competition_id = tc.competition_id
WHERE sportsman_name LIKE '%Ivanov%'
  AND competition_name LIKE '%Региональные%';

-- Выведите города, в которых были установлены мировые рекорды.
SELECT city
FROM t_competition
         JOIN trainee.t_competitions_disciplines tcd on t_competition.competition_id = tcd.competition_id
         JOIN trainee.t_discipline td on td.discipline_id = tcd.discipline_id
WHERE hold_date = set_date;
-- Найдите минимальный разряд спортсменов, которые установили мировой рекорд.
SELECT MIN(rank)
FROM t_sportsman_record
         JOIN trainee.t_discipline td on td.discipline_id = t_sportsman_record.discipline_id
         JOIN trainee.t_sportsman ts on t_sportsman_record.sportsman_id = ts.sportsman_id
WHERE world_record = record_value;
-- Выведите названия соревнований, на которых было установлено максимальное количество мировых рекордов.

-- Определите, спортсмены какой страны участвовали в соревнованиях больше всего.
SELECT country
FROM t_result
         JOIN t_sportsman ON t_result.sportsman_id = t_sportsman.sportsman_id
GROUP BY country
ORDER BY COUNT(*) DESC
LIMIT 1;

-- Измените разряд на 1 тех спортсменов, у которых личный рекорд совпадает с мировым.
UPDATE t_sportsman
SET rank = rank + 1
WHERE (SELECT record_value
       FROM t_sportsman_record
                JOIN trainee.t_discipline td on td.discipline_id = t_sportsman_record.discipline_id) =
      (SELECT world_record
       FROM t_discipline
                JOIN trainee.t_sportsman_record tsr on t_discipline.discipline_id = tsr.discipline_id);

-- Вычислите возраст спортсменов, которые участвовали в соревнованиях в Москве.
SELECT 2023 - year_of_birth AS age
FROM t_sportsman
         JOIN trainee.t_result tr on t_sportsman.sportsman_id = tr.sportsman_id
         JOIN trainee.t_competition tc on tc.competition_id = tr.competition_id;
--

-- Измените дату проведения всех соревнований, проходящих в Москве на 4 дня вперед.
UPDATE t_competition
SET hold_date = hold_date + '4 days';

-- Измените страну у спортсменов, у которых разряд равен 1 или 2, с Италии на Россию.
UPDATE t_sportsman
SET country = 'Russia'
WHERE (rank = 1 OR rank = 2)
  AND country = 'Italy';

-- Измените название соревнований с 'Бег' на 'Бег с препятствиями'
UPDATE t_discipline
SET discipline_description = 'Бег с препятствиями'
WHERE discipline_description = 'Бег';

-- Увеличьте мировой результат на 2 с для соревнований ранее 20-03-2005.
UPDATE t_discipline
SET world_record = world_record + 2
WHERE set_date < '2005-03-20';
-- Уменьшите результаты на 2 с соревнований, которые проводились 20-05-2012 и показанный результат не менее 45 с.
UPDATE t_result
SET result = result - 2
WHERE (SELECT 1
       FROM t_competition
                JOIN trainee.t_result tr on t_competition.competition_id = tr.competition_id
       WHERE hold_date < '20-05-2012'
         AND result < 45);
-- Удалите все результаты соревнований в Москве, участники которых родились не позже 1980 г.
DELETE
FROM t_result
WHERE EXISTS (SELECT 1
              FROM t_competition
                       JOIN trainee.t_result tr on t_competition.competition_id = tr.competition_id
                       JOIN trainee.t_sportsman ts on tr.sportsman_id = ts.sportsman_id
              WHERE tr.competition_id = t_competition.competition_id
                AND city = 'Москва'
                AND year_of_birth < 1980
                AND year_of_birth > 1970);
-- Удалите все соревнования, у которых результат равен 20 с.
DELETE
FROM t_competition
WHERE (SELECT 1
       FROM t_result
                JOIN trainee.t_competition tc on tc.competition_id = t_result.competition_id
       WHERE result = 20);
-- Удалите все результаты спортсменов, которые родились в 2001 году
DELETE
FROM t_result
WHERE (SELECT 1
       FROM t_sportsman
                JOIN trainee.t_result tr on t_sportsman.sportsman_id = tr.sportsman_id
       WHERE year_of_birth=2001)
