## Matching behaviour

### What happens if you remove the go-command from the Seek call in the main function?

As expected, now the program executes in logical chronological order, hence Eva (the last name) can't send to anyone. When the go-command is present, each 'seek' will be a different goroutine, making it fairly random which name is the last one to try to send.

### What happens if you switch the declaration wg := new(sync.WaitGroup) to var wg sync.WaitGroup and the parameter wg \*sync.WaitGroup to wg sync.WaitGroup?

Gives a panic error because of 'negative WaitGroup counter'. That makes sense because using \* before a variable is used to dereference the pointer. That is changing the value stored at sync.WaitGroups location. With different goroutines, we want to decrement the same waitgroup with each wg.Done() statement in each goroutine, (like decrementing an index). If not the \* now we dont change the same waitgroup with each goroutine.

### What happens if you remove the buffer on the channel match?

As expected, it gives us a deadlock! This is because a unbuffered channel cannot hold a value, it needs to send when it recieves.

### What happens if you remove the default-case from the case-statement in the main function?

In this state of the program nothing changes, because we have five names, two senders and two recievers. One name will always remain alone without reciever (as long as the array is of odd size).

| Variant      | Runtime (ms) |
| ------------ | -----------: |
| singleworker |         1845 |
| mapreduce    |         1330 |
