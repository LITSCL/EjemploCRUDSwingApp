SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `dbejemplocrudswingapp` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `dbejemplocrudswingapp` ;

-- -----------------------------------------------------
-- Table `dbejemplocrudswingapp`.`categoria`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbejemplocrudswingapp`.`categoria` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `nombre` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `nombre_UNIQUE` (`nombre` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbejemplocrudswingapp`.`producto`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbejemplocrudswingapp`.`producto` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `nombre` VARCHAR(45) NOT NULL ,
  `descripcion` VARCHAR(90) NOT NULL ,
  `precio` DOUBLE NOT NULL ,
  `stock` INT NOT NULL ,
  `imagen` VARCHAR(90) NULL ,
  `categoria_id` INT NOT NULL ,
  PRIMARY KEY (`id`, `categoria_id`) ,
  INDEX `fk_producto_categoria_idx` (`categoria_id` ASC) ,
  CONSTRAINT `fk_producto_categoria`
    FOREIGN KEY (`categoria_id` )
    REFERENCES `dbejemplocrud`.`categoria` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `dbejemplocrudswingapp` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
