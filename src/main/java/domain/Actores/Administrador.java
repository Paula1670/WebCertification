/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain.Actores;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

/**
 *
 * @author alejandro
 */
@Entity
@Access(AccessType.PROPERTY)
public class Administrador extends Actor {
    public Administrador(){
        super();
    }
    
}
