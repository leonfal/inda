from email.errors import HeaderMissingRequiredValue
from re import A
import pygame, sys
import pygame
from pygame.locals import *
import random
from pygame import mixer

pygame.init()
pygame.mixer.init()

# the soundtrack
soundtrack = pygame.mixer.Sound("assets/soundtrack.wav")
soundtrack.set_volume(0.1)
soundtrack.play(-1)

# the sound effects for power ups and taking damage
addHeart_sound = pygame.mixer.Sound("assets/healthUp_sound.wav")
freeze_sound = pygame.mixer.Sound("assets/freeze_sound.wav")
unlimitedBullets_sound = pygame.mixer.Sound("assets/unlimited_sound.wav")
tripleShots_sound = pygame.mixer.Sound("assets/tripleshots_sound.wav")
damage_sound = pygame.mixer.Sound("assets/damage_sound.wav")

freeze_sound.set_volume(0.5)
unlimitedBullets_sound.set_volume(0.3)
tripleShots_sound.set_volume(0.5)

# some constants
winnerFont = pygame.font.SysFont('Arial black', 120)
winnerBlue = winnerFont.render("Red Jet Wins!", True, (226, 59, 58))
winnerRed = winnerFont.render("Blue Jet Wins!", True, (101, 175, 184))
FPS = 60

asteroidVelocity = 3

WIDTH, HEIGHT = 1000, 625
jetH, jetW = 60, 60

randomY = random.randint(40, HEIGHT-40)
randomY2 = random.randint(40,HEIGHT-40)
randomY3 = random.randint(40,HEIGHT-40)
randomY4 = random.randint(40,HEIGHT-40)

redHit = pygame.USEREVENT + 1
blueHit = pygame.USEREVENT + 2

blueAddHeart = pygame.USEREVENT + 3
redAddHeart = pygame.USEREVENT + 4

blueUnlimitedBullets = pygame.USEREVENT + 5
redUnlimitedBullets = pygame.USEREVENT + 6

blueFreeze = pygame.USEREVENT + 7
redFreeze = pygame.USEREVENT + 8

blueTripleShots = pygame.USEREVENT + 9
redTripleShots = pygame.USEREVENT + 10

screen = pygame.display.set_mode((WIDTH, HEIGHT))
pygame.display.set_caption("Jet Wars")

blueJetImage = pygame.image.load("assets/blue_jet.png").convert_alpha()
redJetImage = pygame.image.load("assets/red_jet.png").convert_alpha()
healthHeartImage = pygame.image.load("assets/health_heart.png").convert_alpha()
asteroidImage = pygame.image.load("assets/boulder.png").convert_alpha()
bgImage = pygame.image.load("assets/space_background.jpeg").convert_alpha()
addHeartImage = pygame.image.load("assets/add_heart.png").convert_alpha()
unlimitedBulletsImage = pygame.image.load("assets/unlimited_bullets.png").convert_alpha()
freezeImage = pygame.image.load("assets/freeze.png").convert_alpha()
tripleShotsImage = pygame.image.load("assets/triple_shots.png").convert_alpha()

blueJet = pygame.transform.rotate(pygame.transform.smoothscale(blueJetImage, (jetW, jetH)), 90)
redJet = pygame.transform.rotate(pygame.transform.smoothscale(redJetImage, (jetW, jetH)), 270)
healthHeart = pygame.transform.smoothscale(healthHeartImage, (25, 22))
asteroid1 = pygame.transform.smoothscale(asteroidImage, (35,35))
addHeart = pygame.transform.smoothscale(addHeartImage, (40,40))
unlimitedBullets = pygame.transform.smoothscale(unlimitedBulletsImage, (40,40))
freeze = pygame.transform.smoothscale(freezeImage, (40,40))
tripleShots = pygame.transform.smoothscale(tripleShotsImage, (40,40))

# function to draw everything on screen
def drawScreen(red, blue, leftasteroids, rightasteroids, asteroidAngle, redBullets, blueBullets, redHearts, blueHearts, addHeartList, unlimitedBulletsList, freezeList, tripleShotsList):
  screen.blit(bgImage, (0,0))

  pygame.draw.line(screen,(228, 217, 255), (WIDTH/2+jetW/2, 0), (WIDTH/2+jetW/2, HEIGHT), 5)
  pygame.draw.line(screen,(228, 217, 255), (WIDTH/2-jetW/2, 0), (WIDTH/2-jetW/2, HEIGHT), 5)

  for pos in range(redHearts):
      screen.blit(healthHeart, (pos*40+200, 25))
  for pos in range(blueHearts):
      screen.blit(healthHeart, (pos*40+WIDTH//2+200, 25))


  for bullet in redBullets:
        pygame.draw.rect(screen, (226, 59, 58), bullet)

  for bullet in blueBullets:
        pygame.draw.rect(screen, (101, 175, 184), bullet)

  screen.blit(blueJet, (blue.x, blue.y))
  screen.blit(redJet, (red.x, red.y))

  rotated_image = pygame.transform.rotate(asteroid1, asteroidAngle)
  # to rotate around center in every position on screen


  for asteroid in leftasteroids:
        screen.blit((rotated_image), (asteroid.x - int(rotated_image.get_width() / 2), asteroid.y - int(rotated_image.get_height() / 2)))

  for asteroid2 in rightasteroids:
        screen.blit((rotated_image), (asteroid2.x - int(rotated_image.get_width() / 2), asteroid2.y - int(rotated_image.get_height() / 2)))


  if len(addHeartList) == 1:
      screen.blit(addHeart, (WIDTH/2-20, randomY))

  if len(unlimitedBulletsList) != 0:
      screen.blit(unlimitedBullets, (WIDTH/2 - 20, randomY2))

  if len(freezeList) != 0:
      screen.blit(freeze, (WIDTH/2 - 20, randomY3))

  if len(tripleShotsList) != 0:
      screen.blit(tripleShots, (WIDTH/2 - 20, randomY4))

  pygame.display.update()

# function to take key presses by user playing red
def handle_red(key_pressed, red, redVelocity):
  if key_pressed[pygame.K_w] and red.y > 0: # up
    red.y -= redVelocity
  if key_pressed[pygame.K_a] and red.x > 0: # left
    red.x -= redVelocity
  if key_pressed[pygame.K_s] and red.y < HEIGHT-jetH: # down
    red.y += redVelocity
  if key_pressed[pygame.K_d] and red.x < WIDTH//2-jetW/2: # right
    red.x += redVelocity

# function to take key presses by user playing blue
def handle_blue(key_pressed, blue, blueVelocity):
  if key_pressed[pygame.K_UP] and blue.y > 0: # up
    blue.y -= blueVelocity
  if key_pressed[pygame.K_LEFT] and blue.x > WIDTH//2-jetW//2: # left
    blue.x -= blueVelocity
  if key_pressed[pygame.K_DOWN] and blue.y < HEIGHT-jetH: # down
    blue.y += blueVelocity
  if key_pressed[pygame.K_RIGHT] and blue.x < WIDTH-jetW: #right
    blue.x += blueVelocity

# function to handle bullet movement, collision and removal when exiting the screen
def handle_bullets(redBullets, blueBullets, red, blue, redbulletVelocity, blueBulletVelocity):
  for bullet in redBullets:
        bullet.x += redbulletVelocity
        if blue.colliderect(bullet):
              pygame.event.post(pygame.event.Event(blueHit))
              redBullets.remove(bullet)

        elif bullet.x > WIDTH:
              redBullets.remove(bullet)

  for bullet in blueBullets:
        bullet.x -= blueBulletVelocity
        if red.colliderect(bullet):
              pygame.event.post(pygame.event.Event(redHit))
              blueBullets.remove(bullet)

        elif bullet.x < 0:
              blueBullets.remove(bullet)

def handle_addHeart(red, blue, addHeartRect, addHeartList):
      if blue.colliderect(addHeartRect) and len(addHeartList) != 0:
            pygame.event.post(pygame.event.Event(blueAddHeart))
            addHeartList.remove(addHeartRect)
      if red.colliderect(addHeartRect) and len(addHeartList) != 0:
            pygame.event.post(pygame.event.Event(redAddHeart))
            addHeartList.remove(addHeartRect)


def handle_unlimitedBullets(red, blue, unlimitedBulletsRect, unlimitedBulletsList):
      if blue.colliderect(unlimitedBulletsRect) and len(unlimitedBulletsList) != 0:
            pygame.event.post(pygame.event.Event(blueUnlimitedBullets))
            unlimitedBulletsList.remove(unlimitedBulletsRect)
      if red.colliderect(unlimitedBulletsRect) and len(unlimitedBulletsList) != 0:
            pygame.event.post(pygame.event.Event(redUnlimitedBullets))
            unlimitedBulletsList.remove(unlimitedBulletsRect)


def handle_freeze(red, blue, freezeRect, freezeList):
      if blue.colliderect(freezeRect) and len(freezeList) != 0:
            pygame.event.post(pygame.event.Event(redFreeze))
            freezeList.remove(freezeRect)
      if red.colliderect(freezeRect) and len(freezeList) != 0:
            pygame.event.post(pygame.event.Event(blueFreeze))
            freezeList.remove(freezeRect)


def handle_tripleShots(red, blue, tripleShotsRect, tripleShotsList):
      if blue.colliderect(tripleShotsRect) and len(tripleShotsList) != 0:
            pygame.event.post(pygame.event.Event(blueTripleShots))
            tripleShotsList.remove(tripleShotsRect)
      if red.colliderect(tripleShotsRect) and len(tripleShotsList) != 0:
            pygame.event.post(pygame.event.Event(redTripleShots))
            tripleShotsList.remove(tripleShotsRect)


def handle_asteroids(leftasteroids, rightasteroids, red, blue):
      for asteroid in leftasteroids:
            randX = random.randint(0, WIDTH//2)
            asteroid.x += asteroidVelocity
            asteroid.y += asteroidVelocity
            if red.colliderect(asteroid):
                  pygame.event.post(pygame.event.Event(redHit))
                  asteroid.x = randX
                  asteroid.y = 0
            if blue.colliderect(asteroid):
                  pygame.event.post(pygame.event.Event(blueHit))
                  asteroid.x = randX
                  asteroid.y = 0
            if asteroid.x > WIDTH or asteroid.y > HEIGHT:
                  asteroid.x = randX
                  asteroid.y = 0

      for asteroid in rightasteroids:
            randX = random.randint(WIDTH//2, WIDTH)
            asteroid.x -= asteroidVelocity
            asteroid.y += asteroidVelocity
            if red.colliderect(asteroid):
                  pygame.event.post(pygame.event.Event(redHit))
                  asteroid.x = randX
                  asteroid.y = 0
            if blue.colliderect(asteroid):
                  pygame.event.post(pygame.event.Event(blueHit))
                  asteroid.x = randX
                  asteroid.y = 0
            if asteroid.x < 0 or asteroid.y > HEIGHT:
                  asteroid.x = randX
                  asteroid.y = 0



# function that draws out a text that declares the winner
def drawWinner(text):
  if text == "Blue Jet Wins!":
      drawText = winnerFont.render(text, True, (101, 175, 184))
  else:
      drawText = winnerFont.render(text, True, (226, 59, 58))
  screen.blit(drawText, (WIDTH//2 - drawText.get_width()//2, HEIGHT//2 - drawText.get_height()//2))
  pygame.display.update()
  pygame.time.delay(5000)

def main():
  randomAsteroidPos1 = -1 * random.randint(0, 500)
  randomAsteroidPos2 = -1 * random.randint(0, 500)
  randomAsteroidPos3 = -1 * random.randint(0, 500)
  randomAsteroidPos4 = -1 * random.randint(0, 500)
  randomAsteroidPos5 = -1 * random.randint(0, 500)
  randomAsteroidPos6 = -1 * random.randint(0, 500)



  red = pygame.Rect(140, 300, jetW, jetH)
  blue = pygame.Rect(800, 300, jetW, jetH)
  addHeartRect = pygame.Rect(WIDTH/2-20, randomY, 40, 40)
  unlimitedBulletsRect = pygame.Rect(WIDTH/2-20, randomY2, 40, 40)
  freezeRect = pygame.Rect(WIDTH/2-20, randomY3, 40, 40)
  tripleShotsRect = pygame.Rect(WIDTH/2-20, randomY4, 40, 40)

  a1 = pygame.Rect(0, randomAsteroidPos1,4,4)
  a2 = pygame.Rect(200, randomAsteroidPos2, 4,4)
  a3 = pygame.Rect(400, randomAsteroidPos3, 4,4)
  a4 = pygame.Rect(600, randomAsteroidPos4, 4,4)
  a5 = pygame.Rect(800, randomAsteroidPos5, 4,4)
  a6 = pygame.Rect(1000, randomAsteroidPos6, 4,4)
  leftasteroids = [a1, a2, a3]
  rightasteroids = [a4, a5, a6]

  # Creating lists that stores bullets
  redBullets = []
  blueBullets = []

  addHeartList = []
  unlimitedBulletsList = []
  freezeList = []
  tripleShotsList = []

  redVelocity = 4
  blueVelocity = 4

  redBulletVelocity = 9
  blueBulletVelocity = 9

  redBulletLimit = 4
  blueBulletLimit = 4

  redHearts = 3
  blueHearts = 3

  redTripleShot = False
  blueTripleShot = False


  clock = pygame.time.Clock()
  run = True
  # the asteroids rotation angle
  angle = 0
  # the pseudo timer for powerups to pop up
  counter = 0

  freezeCounter = 0
  unlimitedBulletsCounter = 0
  tripleShotsCounter = 0
  while run:  # main game loop
    randomChooser = random.randint(1, 4)

    for event in pygame.event.get():
      if event.type == pygame.QUIT:
        run = False;
        pygame.quit()
      # Shooting bullets in the event of a keypress for shooting
      if event.type == pygame.KEYDOWN:
          if event.key == pygame.K_q and len(redBullets) < redBulletLimit:
                bullet = pygame.Rect(red.x + jetW, red.y + jetH//2, 10, 5)
                redBullets.append(bullet)

                if redTripleShot == True:
                  bullet = pygame.Rect(red.x + jetW, red.y + jetH//2+15, 10, 5)
                  redBullets.append(bullet)
                  bullet = pygame.Rect(red.x + jetW, red.y + jetH//2-15, 10, 5)
                  redBullets.append(bullet)
          if event.key == pygame.K_m and len(blueBullets) < blueBulletLimit:
                bullet = pygame.Rect(blue.x, blue.y + jetH//2, 10, 5)
                blueBullets.append(bullet)

                if blueTripleShot == True:
                  bullet = pygame.Rect(blue.x, blue.y + jetH//2+15, 10, 5)
                  blueBullets.append(bullet)
                  bullet = pygame.Rect(blue.x, blue.y + jetH//2-15, 10, 5)
                  blueBullets.append(bullet)


      # chooses to spawn a random powerup
      if counter == 100:

            addHeartList = []
            unlimitedBulletsList = []
            freezeList = []
            tripleShotsList = []


            if randomChooser == 1 and len(addHeartList) == 0:
                  addHeartList.append(addHeartRect)
                  counter = 0

            elif randomChooser == 2 and len(freezeList) == 0:
                  freezeList.append(freezeRect)
                  counter = 0

            elif randomChooser == 3 and len(unlimitedBulletsList) == 0:
                  unlimitedBulletsList.append(unlimitedBulletsRect)
                  counter = 0

            elif randomChooser == 4 and len(tripleShotsList) == 0:
                  tripleShotsList.append(tripleShotsRect)
                  counter = 0

      counter = counter + 1


      # makes powerups only last for a certain amount of time

      # time limit for freeze powerup
      if redVelocity == 3 or blueVelocity == 3:
            freezeCounter += 1
            if freezeCounter == 100:
                  redVelocity = 5
                  blueVelocity = 5
                  redBulletVelocity = 9
                  blueBulletVelocity = 9
                  freezeCounter = 0

      # time limit for unlimited bullets powerup
      if blueBulletLimit % 100 == 0:
            unlimitedBulletsCounter += 1
            if unlimitedBulletsCounter == 100 and blueTripleShot == True:
                  blueBulletLimit = 12
                  unlimitedBulletsCounter = 0
            if unlimitedBulletsCounter == 100 and blueTripleShot == False:
                  blueBulletLimit = 4
                  unlimitedBulletsCounter = 0


      if redBulletLimit % 100 == 0:
            unlimitedBulletsCounter += 1
            if unlimitedBulletsCounter == 100 and redTripleShot == True:
                  redBulletLimit = 12
                  unlimitedBulletsCounter = 0
            if unlimitedBulletsCounter == 100 and redTripleShot == False:
                  redBulletLimit = 4
                  unlimitedBulletsCounter = 0

      # time limit for triple shots powerup
      if blueTripleShot == True:
            tripleShotsCounter += 1
            if tripleShotsCounter == 100 and blueBulletLimit != 100:
                  blueBulletLimit = blueBulletLimit//3
                  blueTripleShot = False
                  tripleShotsCounter = 0
            if tripleShotsCounter == 100 and blueBulletLimit == 100:
                  blueTripleShot = False
                  tripleShotsCounter = 0

      if redTripleShot == True:
            tripleShotsCounter += 1
            if tripleShotsCounter == 100 and redBulletLimit != 100:
                  redBulletLimit = redBulletLimit//3
                  redTripleShot = False
                  tripleShotsCounter = 0
            if tripleShotsCounter == 100 and redBulletLimit == 100:
                  redTripleShot = False
                  tripleShotsCounter = 0


      if event.type == redHit:
            redHearts -= 1
            damage_sound.play()
      if event.type == blueHit:
            blueHearts -= 1
            damage_sound.play()

      if event.type == blueAddHeart:
            blueHearts += 1
            addHeart_sound.play()
      if event.type == redAddHeart:
            redHearts += 1
            addHeart_sound.play()

      if event.type == blueUnlimitedBullets and blueBulletLimit < 100:
            blueBulletLimit = 100
            unlimitedBullets_sound.play()
      if event.type == redUnlimitedBullets and redBulletLimit < 100:
            redBulletLimit = 100
            unlimitedBullets_sound.play()

      if event.type == redFreeze:
            redVelocity = 3
            redBulletVelocity = 6
            freeze_sound.play()
      if event.type == blueFreeze:
            blueVelocity = 3
            blueBulletVelocity = 6
            freeze_sound.play()

      if event.type == blueTripleShots and blueTripleShot == False:
            blueTripleShot = True
            blueBulletLimit = blueBulletLimit*3
            tripleShots_sound.play()

      if event.type == redTripleShots and redTripleShot == False:
            redTripleShot = True
            redBulletLimit = redBulletLimit*3
            tripleShots_sound.play()

    winnerText = ""

    key_pressed = pygame.key.get_pressed()

    handle_red(key_pressed, red, redVelocity)
    handle_blue(key_pressed, blue, blueVelocity)


    angle += 6
    handle_asteroids(leftasteroids, rightasteroids, red, blue)

    handle_bullets(redBullets, blueBullets, red, blue, redBulletVelocity, blueBulletVelocity)

    handle_addHeart(red, blue, addHeartRect, addHeartList)
    handle_unlimitedBullets(red, blue, unlimitedBulletsRect, unlimitedBulletsList)
    handle_freeze(red, blue, freezeRect, freezeList)
    handle_tripleShots(red, blue, tripleShotsRect, tripleShotsList)


    drawScreen(red, blue, leftasteroids, rightasteroids, angle, redBullets, blueBullets, redHearts, blueHearts, addHeartList, unlimitedBulletsList, freezeList, tripleShotsList)


    if redHearts <=  0:
          winnerText = "Blue Jet Wins!"

    if blueHearts <=  0:
          winnerText = "Red Jet Wins!"

    if winnerText != "":
          drawWinner(winnerText)
          break

    clock.tick(FPS)

  main() # Restarts the game rather than quitting it in the event of a win


if __name__ == "__main__":
  main()
