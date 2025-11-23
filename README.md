A simple, fast Java tool that keeps an eye on your files. It works by creating a (SHA-256 hash) for each file you’re monitoring. Whenever you check a file, it recalculates the hash and compares it to the one stored earlier.
If the file hasn’t changed → it’s OK.
If the file was modified → it alerts you.
If the file is missing → it tells you.
