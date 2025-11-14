# Playlist Manager
A **Java CLI application** that simulates a music playlist.  
Starting with a simple main menu, the user can **add/remove songs** (with and without indices), **view songs/view recently played songs**, **shuffle**, **print**, and **search/sort** through two different kind of playlists.

---

## Features
- Dual data structure support (ArrayList and LinkedList)
- Input validation for all user entries
- Safe edge case handling
- Flexible searching
- Multi-criteria sorting (title, artist, duration, playcount)
- Custom LinkedList implementation with capacity limit (recently played queue)
- Easily navigable and clean CLI
- Fully object-oriented design using inheritance and polymorphism

---

## Examples
### Main Menu
```
Playlist Manager

========== MENU ==========

1) Add song
2) Insert song at a position
3) Remove song at a position
4) View songs
5) Shuffle playlist
6) Print playlist
7) Sort playlist
8) Search
9) View recently played songs
0) Switch playlist type

Type 'EXIT' at anytime to return to menu
From the menu, enter 'EXIT' again to close program
>
```
### Adding a Song
```
>1

======= ADD SONG =======

Enter song's title
>exit

'EXIT' input detected
Is 'EXIT' the title/artist/genre?

1) Yes
2) No
>1

Enter song's artist
>Artist Name

Enter song's genre
>Genre Name

Enter song's duration in seconds
>180

Successfully added: exit!

Press 'ENTER' to continue
```
### Viewing a Song (This adds it to Recently Played)
```
>4

======= VIEW SONG =======

Enter the index of the song which you wish to view
>0

===== SONG AT INDEX 0 =====

Title:  Blinding Lights
Artist: The Weeknd
Genre:  synth-pop

|---------------------| 200 sec
      << |> || >>

Plays:  1

===========================

Press 'ENTER' to continue
```
### Printing Playlist
```
>6

======= PRINT PLAYLIST =======
Printing...
Printing...

========== PLAYLIST ==========

Title:  Blinding Lights
Artist: The Weeknd
Genre:  synth-pop

|---------------------| 200 sec
      << |> || >>

Plays:  2

==============================

Title:  Levitating
Artist: Dua Lipa
Genre:  disco-pop

|---------------------| 203 sec
      << |> || >>

Plays:  1

==============================

Title:  Save Your Tears
Artist: The Weeknd
Genre:  synth-pop

|---------------------| 215 sec
      << |> || >>

Plays:  0

==============================


Press 'ENTER' to continue
```
### Viewing Recently Played Queue
```
>9

======= RECENTLY PLAYED =======

Title:  Levitating
Artist: Dua Lipa
Genre:  disco-pop

|---------------------| 203 sec
      << |> || >>

Plays:  1

===============================

Title:  Blinding Lights
Artist: The Weeknd
Genre:  synth-pop

|---------------------| 200 sec
      << |> || >>

Plays:  2

===============================

    ^^ MOST RECENT TRACK ^^    

Press 'ENTER' to continue
```
### Sorting
```
>7

======= SORT PLAYLIST =======

How would you like to sort your playlist?

1) Title
2) Artist
3) Duration
4) Playcount
>1

Successfully Sorted!

Press 'ENTER' to continue
```
### Searching
```
>8

======= SEARCH PLAYLIST =======

How would you like to search through your playlist?

1) Title
2) Artist
3) Genre
>2

Enter song's artist
>The Weeknd

====== SEARCH RESULTS ======

Title:  Blinding Lights
Artist: The Weeknd
Genre:  synth-pop

|---------------------| 200 sec
      << |> || >>

Plays:  2

==============================

Title:  Save Your Tears
Artist: The Weeknd
Genre:  synth-pop

|---------------------| 215 sec
      << |> || >>

Plays:  0

==============================

Press 'ENTER' to continue
```
### Switching Data Structure Type
```
>0

======= SWITCH TYPE =======

!!SWITCHED TO LINKED LIST!!

Press 'ENTER' to continue
```
### Quitting from Menu
```
>exit

====== QUITTING PROGRAM ======
```

---

### Author
Vladislav Lamakin  
lamakinvladislav@gmail.com  
https://github.com/3dimir
