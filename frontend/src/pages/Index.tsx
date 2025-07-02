
import Header from '@/components/Header';
import Hero from '@/components/Hero';
import BestSellers from '@/components/BestSellers';
import Stats from '@/components/Stats';
import Footer from '@/components/Footer';

const Index = () => {
  return (
    <div className="min-h-screen bg-white">
      <Header />
      <Hero />
      <BestSellers />
      <Stats />
      <Footer />
    </div>
  );
};

export default Index;
