import 'package:firebase_auth/firebase_auth.dart';
import '../models/user_model.dart';

class AuthService {
  final FirebaseAuth _auth = FirebaseAuth.instance;

  Future<UserModel> login(String email, String password) async {
    try {
      final cred = await _auth.signInWithEmailAndPassword(
        email: email,
        password: password,
      );
      return _toModel(cred.user!);
    } on FirebaseAuthException catch (e) {
      throw Exception(_message(e.code));
    }
  }

  Future<UserModel> register(String email, String password) async {
    try {
      final cred = await _auth.createUserWithEmailAndPassword(
        email: email,
        password: password,
      );
      return _toModel(cred.user!);
    } on FirebaseAuthException catch (e) {
      throw Exception(_message(e.code));
    }
  }

  Future<void> logout() => _auth.signOut();

  UserModel _toModel(User user) {
    final email = user.email ?? '';
    return UserModel(
      email: email,
      name: user.displayName ?? email.split('@').first,
    );
  }

  String _message(String code) {
    switch (code) {
      case 'invalid-credential':
      case 'wrong-password':
      case 'user-not-found':
        return 'Email o contraseña incorrectos';
      case 'invalid-email':
        return 'El email no es válido';
      case 'email-already-in-use':
        return 'Ya existe una cuenta con ese email';
      case 'weak-password':
        return 'La contraseña es demasiado débil';
      case 'too-many-requests':
        return 'Demasiados intentos, prueba más tarde';
      case 'network-request-failed':
        return 'Sin conexión a internet';
      default:
        return 'Error de autenticación ($code)';
    }
  }
}