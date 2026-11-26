import 'package:flutter_test/flutter_test.dart';
import 'package:mi_app/main.dart';

void main() {
  testWidgets('La app arranca y muestra HomeView', (tester) async {
    await tester.pumpWidget(const MyApp());
    expect(find.text('Proyecto base listo'), findsOneWidget);
  });
}