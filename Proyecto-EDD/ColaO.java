public class ColaO{
    private int tam;
    private int primero;
    private int ultimo;
    private int [] arreglo;
    public ColaO(int tam){
        this.tam=tam;
        this.primero=-1;
        this.ultimo=-1;
        this.arreglo=new int[this.tam];
    }
    public boolean estaLlena (){
        return this.ultimo==tam-1;
    }
    public boolean estaVacia (){
        return this.primero==-1 && this.ultimo==-1;
    }
    public boolean agregar (int valor){
        if(estaLlena()){
            return false;
        }
        if(estaVacia()){
            this.primero ++;
            this.ultimo ++;}
        else{
            this.ultimo ++;
        }
        this.arreglo[this.ultimo]=valor;
        return true;
    }
    public int eliminar()throws Exception {
        if (estaVacia()) {
            throw new Exception("Cola vacía");
        }
        int valor = this.arreglo[this.primero];
        if(this.primero==this.ultimo){
        this.primero=-1;
        this.ultimo=-1;
        }else{
            this.primero++;
        }
        return valor;
    }
}
