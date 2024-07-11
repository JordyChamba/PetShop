package com.proyect.petshop.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
    private long id;
    private String nombre;
    private double precio;
    private int imageResourceId;
    private int cantidad;
    private boolean inCart; // Campo para verificar si está en el carrito
    private boolean active; // Campo para verificar si el producto está activo o eliminado
    private int counter;

    public Product(String nombre, double precio, int imageResourceId, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.imageResourceId = imageResourceId;
        this.cantidad = cantidad;
        this.inCart = false; // Inicialmente no está en el carrito
        this.active = true; // Inicialmente está activo
        this.counter = 0;
    }

    public Product() {}

    protected Product(Parcel in) {
        id = in.readLong();
        nombre = in.readString();
        precio = in.readDouble();
        imageResourceId = in.readInt();
        cantidad = in.readInt();
        inCart = in.readByte() != 0;
        active = in.readByte() != 0;
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(nombre);
        dest.writeDouble(precio);
        dest.writeInt(imageResourceId);
        dest.writeInt(cantidad);
        dest.writeByte((byte) (inCart ? 1 : 0));
        dest.writeByte((byte) (active ? 1 : 0));
    }

    // Getters and setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public boolean isInCart() {
        return inCart;
    }

    public void setInCart(boolean inCart) {
        this.inCart = inCart;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getCounter() {
        return counter;
    }

    public void incrementCounter() {
        counter++;
    }
}
