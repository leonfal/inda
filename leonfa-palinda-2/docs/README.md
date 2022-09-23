### What happens if you switch the order of the statements `wgp.Wait()` and `close(ch)` in the end of the `main` function?

- If we switch the `wgp.Wait()` and `close(ch)` statements the channel will close before all goroutines has time to complete. If we run the code with the statements switched we can observe that different goroutines cancels, fairly random.

### What happens if you move the `close(ch)` from the `main` function and instead close the channel in the end of the function `Produce`?

- This will result in a so called panic scenario. The reason why is because the `Consume` function tries to send on closed channel.

### What happens if you remove the statement `close(ch)` completely?

- Actually nothing happens, because the `wgp.Wait()` let all goroutines finish before the main thread finishes and closes all threads. Basically by letting the main thread finish last, it acts as the close to the channel (and threads).

### What happens if you increase the number of consumers from 2 to 4?

- The only change is the change in time. Because there are more consumers (recievers in this case) the producers (senders) can send to 4 recievers concurrently rather than 2 recievers. This results in a code runtime of approx. half the time.

### Can you be sure that all strings are printed before the program stops?

- No, because if we have equally as many producers as consumers and because the waitgroup waits for `Produce` to finish with the statement `wg.Done()` and the `Consume` function could finish before.
