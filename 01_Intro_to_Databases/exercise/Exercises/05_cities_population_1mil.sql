-- 5. The name, state abbreviation, and population of cities with a population greater than 1,000,000 people (10 rows)

SELECT city_name, state_abbreviation, population from city where population > 1000000;