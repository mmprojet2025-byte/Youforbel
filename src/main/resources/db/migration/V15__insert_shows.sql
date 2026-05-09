INSERT INTO shows
(slug, title, description, poster_url, location_id, bookable, price, created_at, updated_at)

VALUES

(
'hamlet',
'Hamlet',
'Une tragédie de Shakespeare.',
'https://picsum.photos/300/200',
1,
true,
25.00,
NOW(),
NOW()
),

(
'concert-jazz',
'Concert Jazz',
'Soirée musicale jazz.',
'https://picsum.photos/300/201',
2,
true,
18.50,
NOW(),
NOW()
),

(
'exposition-art',
'Exposition Art',
'Exposition d’art contemporain.',
'https://picsum.photos/300/202',
3,
false,
0,
NOW(),
NOW()
);
